/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.oozie.extensions;

import org.apache.commons.lang.StringUtils;
import org.apache.oozie.command.coord.CoordCommandUtils;
import org.apache.oozie.coord.CoordELFunctions;
import org.apache.oozie.util.ELEvaluator;
import org.apache.oozie.util.XLog;

import java.util.Calendar;

public class OozieELExtensions {

    private enum TruncateBoundary {
        NONE, DAY, MONTH, QUARTER, YEAR;
    }

    private static final String PREFIX = "elext:";
    public static final String COORD_CURRENT = "coord:current";

    public static String ph1_dataIn_echo(String dataInName, String part) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        String val = (String) eval.getVariable("oozie.dataname." + dataInName);
        if (val == null || !val.equals("data-in")) {
            XLog.getLog(CoordELFunctions.class).error("data_in_name " + dataInName + " is not valid");
            throw new RuntimeException("data_in_name " + dataInName + " is not valid");
        }
        return PREFIX + "dataIn('" + dataInName + "', '" + part + "')";
    }


    public static String ph3_dataIn(String dataInName, String part) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        String uristr = (String) eval.getVariable(".datain." + dataInName);
        Boolean unresolved = (Boolean) eval.getVariable(".datain." + dataInName + ".unresolved");
        if (unresolved != null && unresolved) {
            throw new RuntimeException("There are unresolved instances in " + uristr);
        }
        
        if(StringUtils.isNotEmpty(uristr) && StringUtils.isNotEmpty(part)) {
            String[] uris = uristr.split(",");
            StringBuilder mappedUris = new StringBuilder();
            for(String uri:uris) {
                if(uri.trim().length() == 0)
                    continue;
                if(mappedUris.length() > 0)
                    mappedUris.append(",");
                mappedUris.append(uri).append("/").append(part);
            }
             return mappedUris.toString();
        }
        return uristr;
    }
    
    public static String ph1_now_echo(int hr, int min) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "now("+ hr + "," + min + ")"; // Unresolved
    }

    public static String ph1_today_echo(int hr, int min) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "today(" + hr + ", " + min + ")"; // Unresolved
    }

    public static String ph1_todayWithOffset_echo(int hr, int min, int offsetMin) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "todayWithOffset(" + hr + ", " + min + ", " + offsetMin + ")"; // Unresolved
    }

    public static String ph1_yesterday_echo(int hr, int min) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "yesterday(" + hr + ", " + min + ")"; // Unresolved
    }

    public static String ph1_yesterdayWithOffset_echo(int hr, int min, int offsetMin) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "yesterdayWithOffset(" + hr + ", " + min + ", " + offsetMin + ")"; // Unresolved
    }

    public static String ph1_currentMonth_echo(int day, int hr, int min) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "currentMonth(" + day + ", " + hr + ", " + min + ")"; // Unresolved
    }

    public static String ph1_currentMonthWithOffset_echo(int day, int hr, int min, int offsetMin) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "currentMonth(" + day + ", " + hr + ", " + min + ", " + offsetMin + ")"; // Unresolved
    }

    public static String ph1_lastMonth_echo(int day, int hr, int min) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "lastMonth(" + day + ", " + hr + ", " + min + ")"; // Unresolved
    }

    public static String ph1_lastMonthWithOffset_echo(int day, int hr, int min, int offsetMin) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "lastMonth(" + day + ", " + hr + ", " + min + ", " + offsetMin + ")"; // Unresolved
    }

    public static String ph1_currentYear_echo(int month, int day, int hr, int min) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "currentYear(" + month + ", " + day + ", " + hr + ", " + min + ")"; // Unresolved
    }

    public static String ph1_currentYearWithOffset_echo(int month, int day, int hr,
                                                        int min, int offsetMin) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "currentYear(" + month + ", " + day + ", " + hr + ", " +
                min + ", " + offsetMin + ")"; // Unresolved
    }

    public static String ph1_lastYear_echo(int month, int day, int hr, int min) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "lastYear(" + month + ", " + day + ", " + hr + ", " + min + ")"; // Unresolved
    }

    public static String ph1_lastYearWithOffset_echo(int month, int day, int hr,
                                                     int min, int offsetMin) {
        ELEvaluator eval = ELEvaluator.getCurrent();
        eval.setVariable(".wrap", "true");
        return PREFIX + "lastYear(" + month + ", " + day + ", " + hr + ", " +
                min + ", " + offsetMin + ")"; // Unresolved
    }

    public static String ph2_now_inst(int hr, int min) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.NONE, 0, 0, 0, hr, min, 0);
    }

    public static String ph2_today_inst(int hr, int min) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.DAY, 0, 0, 0, hr, min, 0);
    }

    public static String ph2_todayWithOffset_inst(int hr, int min, int offsetMin) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.DAY, 0, 0, 0, hr, min, offsetMin);
    }

    public static String ph2_yesterday_inst(int hr, int min) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.DAY, 0, 0, -1, hr, min, 0);
    }

    public static String ph2_yesterdayWithOffset_inst(int hr, int min, int offsetMin) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.DAY, 0, 0, -1, hr, min, offsetMin);
    }

    public static String ph2_currentMonth_inst(int day, int hr, int min) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.MONTH, 0, 0, day, hr, min, 0);
    }

    public static String ph2_currentMonthWithOffset_inst(int day, int hr, int min, int offsetMin) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.MONTH, 0, 0, day, hr, min, offsetMin);
    }

    public static String ph2_lastMonth_inst(int day, int hr, int min) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.MONTH, 0, -1, day, hr, min, 0);
    }

    public static String ph2_lastMonthWithOffset_inst(int day, int hr, int min, int offsetMin) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.MONTH, 0, -1, day, hr, min, offsetMin);
    }

    public static String ph2_currentYear_inst(int month, int day, int hr, int min) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.YEAR, 0, month, day, hr, min, 0);
    }

    public static String ph2_currentYearWithOffset_inst(int month, int day,
                                                        int hr, int min, int offsetMin) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.YEAR, 0, month, day, hr, min, offsetMin);
    }

    public static String ph2_lastYear_inst(int month, int day, int hr, int min) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.YEAR, -1, month, day, hr, min, 0);
    }

    public static String ph2_lastYearWithOffset_inst(int month, int day,
                                                     int hr, int min, int offsetMin) throws Exception {
        return mapToCurrentInstance(TruncateBoundary.YEAR, -1, month, day, hr, min, offsetMin);
    }

    private static String evaluateCurrent(String curExpr) throws Exception {
        if(curExpr.equals("")) {
            return curExpr;
        }

        int inst = CoordCommandUtils.parseOneArg(curExpr);
        return CoordELFunctions.ph2_coord_current(inst);
    }

    public static String ph2_now(int hr, int min) throws Exception {
        String inst = ph2_now_inst(hr, min);
        return evaluateCurrent(inst);
    }

    public static String ph2_today(int hr, int min) throws Exception {
        String inst = ph2_today_inst(hr, min);
        return evaluateCurrent(inst);
    }

    public static String ph2_todayWithOffset(int hr, int min, int offsetMin) throws Exception {
        String inst = ph2_todayWithOffset_inst(hr, min, offsetMin);
        return evaluateCurrent(inst);
    }

    public static String ph2_yesterday(int hr, int min) throws Exception {
        String inst = ph2_yesterday_inst(hr, min);
        return evaluateCurrent(inst);
    }

    public static String ph2_yesterdayWithOffset(int hr, int min, int offsetMin) throws Exception {
        String inst = ph2_yesterdayWithOffset_inst(hr, min, offsetMin);
        return evaluateCurrent(inst);
    }

    public static String ph2_currentMonth(int day, int hr, int min) throws Exception {
        String inst = ph2_currentMonth_inst(day, hr, min);
        return evaluateCurrent(inst);
    }

    public static String ph2_currentMonthWithOffset(int day, int hr, int min, int offsetMin) throws Exception {
        String inst = ph2_currentMonthWithOffset_inst(day, hr, min, offsetMin);
        return evaluateCurrent(inst);
    }

    public static String ph2_lastMonth(int day, int hr, int min) throws Exception {
        String inst = ph2_lastMonth_inst(day, hr, min);
        return evaluateCurrent(inst);
    }

    public static String ph2_lastMonthWithOffset(int day, int hr, int min, int offsetMin) throws Exception {
        String inst = ph2_lastMonthWithOffset_inst(day, hr, min, offsetMin);
        return evaluateCurrent(inst);
    }

    public static String ph2_currentYear(int month, int day, int hr, int min) throws Exception {
        String inst = ph2_currentYear_inst(month, day, hr, min);
        return evaluateCurrent(inst);
    }

    public static String ph2_currentYearWithOffset(int month, int day, int hr, int min, int offsetMIn) throws Exception {
        String inst = ph2_currentYearWithOffset_inst(month, day, hr, min, offsetMIn);
        return evaluateCurrent(inst);
    }

    public static String ph2_lastYear(int month, int day, int hr, int min) throws Exception {
        String inst = ph2_lastYear_inst(month, day, hr, min);
        return evaluateCurrent(inst);
    }

    public static String ph2_lastYearWithOffset(int month, int day, int hr, int min, int offsetMin) throws Exception {
        String inst = ph2_lastYearWithOffset_inst(month, day, hr, min, offsetMin);
        return evaluateCurrent(inst);
    }

    /**
     * Maps the dataset time to coord:current(n) with respect to action's nominal time
     * dataset time = truncate(nominal time) + yr + day + month + hr + min
     * @param trunc : Truncate resolution
     * @param yr : Year to add (can be -ve)
     * @param month : month to add (can be -ve)
     * @param day : day to add (can be -ve)
     * @param hr : hr to add (can be -ve)
     * @param min : min to add (can be -ve)
     * @param offsetMinutes - minutes to offset from nominal time
     * @return coord:current(n)
     * @throws Exception : If encountered an exception while evaluating
     */
    //TODO handle the case where action_Creation_time or the n-th instance is earlier than the Initial_Instance of dataset.
    private static String mapToCurrentInstance(TruncateBoundary trunc, int yr, int month,
                                               int day, int hr, int min, int offsetMinutes) throws Exception {
        Calendar nominalInstanceCal = CoordELFunctions.getEffectiveNominalTime();
        if (nominalInstanceCal == null) {
            XLog.getLog(OozieELExtensions.class).warn(
                    "If the initial instance of the dataset is later than the nominal time, an empty string is returned. " +
                            "This means that no data is available at the current-instance specified by the user " +
                    "and the user could try modifying his initial-instance to an earlier time.");
            return "";
        }

        Calendar dsInstanceCal = Calendar.getInstance(CoordELFunctions.getDatasetTZ());
        dsInstanceCal.setTime(nominalInstanceCal.getTime());
        dsInstanceCal.add(Calendar.MINUTE, offsetMinutes);

        //truncate
        switch (trunc) {
            case YEAR:
                dsInstanceCal.set(Calendar.MONTH, 0);
            case MONTH:
                dsInstanceCal.set(Calendar.DAY_OF_MONTH, 1);
            case DAY:
                dsInstanceCal.set(Calendar.HOUR_OF_DAY, 0);
                dsInstanceCal.set(Calendar.MINUTE, 0);
                dsInstanceCal.set(Calendar.SECOND, 0);
                dsInstanceCal.set(Calendar.MILLISECOND, 0);
                break;
            case NONE:    //don't truncate
                break;
            default:
                throw new IllegalArgumentException("Truncation boundary " + trunc + " is not supported");
        }

        //add
        dsInstanceCal.add(Calendar.YEAR, yr);
        dsInstanceCal.add(Calendar.MONTH, month);
        dsInstanceCal.add(Calendar.DAY_OF_MONTH, day);
        dsInstanceCal.add(Calendar.HOUR_OF_DAY, hr);
        dsInstanceCal.add(Calendar.MINUTE, min);

        int[] instCnt = new int[1];
        Calendar compInstCal = CoordELFunctions.getCurrentInstance(dsInstanceCal.getTime(), instCnt);
        if(compInstCal == null) {
            return "";
        }
        int dsInstanceCnt = instCnt[0];

        compInstCal = CoordELFunctions.getCurrentInstance(nominalInstanceCal.getTime(), instCnt);
        if(compInstCal == null) {
            return "";
        }
        int nominalInstanceCnt = instCnt[0];

        return COORD_CURRENT + "(" + (dsInstanceCnt - nominalInstanceCnt) + ")";
    }
}
