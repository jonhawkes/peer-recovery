/*
 * IBM Confidential
 *
 * OCO Source Materials
 *
 * Copyright IBM Corp. 2012
 *
 * The source code for this program is not published or otherwise divested
 * of its trade secrets, irrespective of what has been deposited with the
 * U.S. Copyright Office.
 */
package com.ibm.ws.Transaction.test.impl;

import com.ibm.ws.Transaction.test.XAFlowCallback;

public class XAFlowCallbackImpl implements XAFlowCallback {
    public static void initialize() {

    }

    public static final boolean isEnabled() {
        return true;
    }

    @Override
    public boolean beforeXAFlow(int flowType, int flag) {
        System.out.println("XAFLOWCALLBACK: Before flow type: " + getFlowType(flowType) + ", Before flag: " + getBeforeFlag(flag));
        if (flowType == 2)
        {
            System.out.println("XAFLOWCALLBACK: Bring down server");
            Runtime.getRuntime().halt(1);
        }
        return true;
    }

    @Override
    public boolean afterXAFlow(int flowType, int flag) {
        System.out.println("XAFLOWCALLBACK: After flow type: " + getFlowType(flowType) + ", After flag: " + getAfterFlag(flag));
        return true;
    }

    private String getFlowType(int flowType)
    {
        String strFlowType = "";
        // Flow type definitions
        // FORGET   = 0;
        // PREPARE  = 1;
        // COMMIT   = 2;
        // ROLLBACK = 3;   
        switch (flowType)
        {
            case 1:
                strFlowType = "PREPARE";
                break;
            case 2:
                strFlowType = "COMMIT";
                break;
            case 3:
                strFlowType = "ROLLBACK";
                break;
            case 0:
            default:
                strFlowType = "FORGET";
                break;
        }

        return strFlowType;
    }

    private String getBeforeFlag(int flag)
    {
        String strFlag = "";

        // Before flag definitions
        // FORGET_NORMAL         = 10;
        // PREPARE_NORMAL        = 20;
        // PREPARE_1PC_OPT       = 21;
        // COMMIT_2PC            = 30;
        // COMMIT_1PC_OPT        = 31;
        // ROLLBACK_NORMAL       = 40;
        // ROLLBACK_DUE_TO_ERROR = 41;
        switch (flag)
        {
            case 10:
                strFlag = "FORGET_NORMAL";
                break;
            case 20:
                strFlag = "PREPARE_NORMAL";
                break;
            case 21:
                strFlag = "PREPARE_1PC_OPT";
                break;
            case 30:
                strFlag = "COMMIT_2PC";
                break;
            case 31:
                strFlag = "COMMIT_1PC_OPT";
                break;
            case 40:
                strFlag = "ROLLBACK_NORMAL";
                break;
            case 41:
                strFlag = "ROLLBACK_DUE_TO_ERROR";
                break;
            default:
                strFlag = "UNEXPECTED FLAG " + flag;
                break;
        }

        return strFlag;
    }

    private String getAfterFlag(int flag)
    {
        String strFlag = "";

        // After flag definitions
        // AFTER_SUCCESS         = 50;
        // AFTER_FAIL            = 51;    
        switch (flag)
        {
            case 50:
                strFlag = "SUCCESS";
                break;
            case 51:
                strFlag = "FAIL";
                break;
            default:
                strFlag = "UNEXPECTED FLAG " + flag;
                break;
        }

        return strFlag;
    }
}
