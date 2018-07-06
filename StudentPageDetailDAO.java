/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendnse.StudentDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author Saisam
 */
public class StudentPageDetailDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "Select username, status from tblAccount where username = ? and password = ?  ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    if (!rs.getString("status").matches("dropout")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    private List<MarkTableDetailDTO> listSubject;

    public List<MarkTableDetailDTO> getListSubject() {
        return listSubject;
    }

    public void searchFullSubject(String username) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rstblMarks = null;
        ResultSet rstblSubject = null;
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sqltblMark = "Select subjectID, blockSemester, subjectAvg, status from tblMarks where studentID =?";
                pstm = conn.prepareStatement(sqltblMark);
                pstm.setString(1, username);
                rstblMarks = pstm.executeQuery();

                while (rstblMarks.next()) {
                    String subjectID = rstblMarks.getString("subjectID");
                    String blockSemester = rstblMarks.getString("blockSemester");
                    String block = blockSemester.substring(blockSemester.length() - 1);
                    String year = blockSemester.substring(blockSemester.length() - 6, blockSemester.length() - 2);
                    String semester = blockSemester.substring(0, blockSemester.length() - 6);
                    switch (semester) {
                        case "Fall":
                            semester = "3";
                            break;
                        case "Summer":
                            semester = "2";
                            break;
                        default:
                            semester = "1";
                            break;
                    }

                    Float mark = rstblMarks.getFloat("subjectAvg");
                    String status = rstblMarks.getString("status");
                    String subjectName = "";
                    int credits = 0;
                    String sqltblSubject = "Select subjectName, credits from tblSubject where subjectID =?";
                    pstm = conn.prepareStatement(sqltblSubject);
                    pstm.setString(1, subjectID);
                    rstblSubject = pstm.executeQuery();
                    while (rstblSubject.next()) {
                        subjectName = rstblSubject.getString("subjectName");
                        credits = rstblSubject.getInt("credits");
                    }
                    if (status.equalsIgnoreCase("Fail") || status.equalsIgnoreCase("Not_Started")) {
                        credits = 0;
                    }
                    MarkTableDetailDTO dtoFullSubject = new MarkTableDetailDTO(subjectID, subjectName, block, semester, year, mark, status, credits);
                    if (this.listSubject == null) {
                        this.listSubject = new ArrayList<>();
                    }
                    this.listSubject.add(dtoFullSubject);
                    this.listSubject.sort(Comparator.comparing(MarkTableDetailDTO::getYear)
                            .thenComparing(Comparator.comparing(MarkTableDetailDTO::getSemester))
                            .thenComparing(Comparator.comparing(MarkTableDetailDTO::getBlock))
                    );
                }
            }
        } finally {
            if (rstblSubject != null) {
                rstblSubject.close();
            }
            if (rstblMarks != null) {
                rstblMarks.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public MarkTableDetailDTO selectedSubjectIDToShow(List<MarkTableDetailDTO> list) {
        for (MarkTableDetailDTO item : list) {
            if (item.getStatus().equalsIgnoreCase("Not_Started")) {
                return item;
            }
        }
        list.sort(Comparator.comparing(MarkTableDetailDTO::getYear)
                .thenComparing(Comparator.comparing(MarkTableDetailDTO::getSemester))
                .thenComparing(Comparator.comparing(MarkTableDetailDTO::getBlock))
                .reversed()
        );
        return list.get(0);
    }

    public List<MarkTableDetailDTO> displaySubjectDetail(List<MarkTableDetailDTO> list, String username) throws ClassNotFoundException, SQLException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList<String> listFullSubjectID = new ArrayList<>();
        List<MarkTableDetailDTO> listEachSubjectIDToShow = new ArrayList<>();
        List<MarkTableDetailDTO> listSubjectToShow = new ArrayList<>();
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sqlSubjectID = "Select subjectID from tblMarks where studentID =? group by subjectID";
                pstm = conn.prepareStatement(sqlSubjectID);
                pstm.setString(1, username);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");
                    listFullSubjectID.add(subjectID);
                }
                for (String item : listFullSubjectID) {
                    listEachSubjectIDToShow.clear();
                    for (MarkTableDetailDTO obj : list) {
                        if (item.equalsIgnoreCase(obj.getSubjectID())) {
                            listEachSubjectIDToShow.add(obj);
                        }
                    }
                    listSubjectToShow.add(selectedSubjectIDToShow(listEachSubjectIDToShow));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();

            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listSubjectToShow;
    }

    public String searchStudentDetail(String username) throws SQLException, NamingException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String studentName = "";
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT firstName, lastName, middleName FROM tblStudent WHERE studentID =?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String firstname = rs.getString("firstName");
                    String lastname = rs.getString("lastName");
                    String middlename = rs.getString("middleName");
                    if (null == middlename) {
                        middlename = " ";
                    }
                    if (null == lastname) {
                        lastname = " ";
                    }
                    studentName = firstname + " " + middlename + " " + lastname;
                }
            }   //  end if conn

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return studentName.trim();
    }

    public boolean sendFeedback(MarkTableDetailDTO subject, String StudentID) throws SQLException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Date date = new Date(System.currentTimeMillis());
        String feedbackContent = "Some marks of courses is not correct for subject " + subject.getSubjectID()
                + ", Student ID: " + StudentID
                + ", year: " + subject.getYear()
                + ", semester: " + subject.getSemester()
                + ", block: " + subject.getBlock();
        int countID = Integer.parseInt(subject.getYear() + subject.getSemester() + subject.getBlock());
        try {
            conn = DBUtils.makeConnection();
            if (conn != null) {
                String sqlFbExisted = "Select count (id) from tblFeedback where id=? and studentID =? ";
                pstm = conn.prepareStatement(sqlFbExisted);
                pstm.setInt(1, countID);
                pstm.setString(2, StudentID);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    if (rs.getInt(1) == 0) {
//                        System.out.println("true" + rs.getInt(1));                        
                        String sqlInsertFeedback = "Insert into "
                                + "tblFeedback(id, fbDate, contents, studentID, status) "
                                + "Values(?, ?, ?, ?, ?)";
                        pstm = conn.prepareStatement(sqlInsertFeedback);
                        pstm.setInt(1, countID);
                        pstm.setDate(2, date);
                        pstm.setString(3, feedbackContent);
                        pstm.setString(4, StudentID);
                        pstm.setBoolean(5, false);
                        int row = pstm.executeUpdate();
                        System.out.println("sqlInsertFeedback");
                        if (row > 0) {
                            return true;
                        }
                    } else {
                        String sqlUpdateFeedback = "Update tblFeedback "
                                + "Set fbDate = ? "
                                + "where id=? and studentID =? ";
                        pstm = conn.prepareStatement(sqlUpdateFeedback);
                        pstm.setDate(1, date);
                        pstm.setInt(2, countID);
                        pstm.setString(3, StudentID);
                        int row = pstm.executeUpdate();
                        System.out.println("sqlUpdateFeedback");
                        if (row > 0) {
                            return true;
                        }
                    }
//                    else {
//                        System.out.println("false" + rs.getInt(1));
//                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

}
