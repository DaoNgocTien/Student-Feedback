/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendnse.StudentDetail;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Saisam
 */
public class MarkTableDetailDTO implements Serializable{
    private String subjectID;
    private String subjectName;
    private String block;
    private String semester;
    private String year;
    private float mark;
    private String status;
    private int credits;

    public static Comparator<MarkTableDetailDTO> COMPARE_BY_SEMESTER = 
                    (MarkTableDetailDTO one, MarkTableDetailDTO other) 
                    -> one.getSemester().compareTo(other.getSemester());

    public MarkTableDetailDTO() {
    }

    public MarkTableDetailDTO(String subjectID, String subjectName, String block, String semester, String year, float mark, String status, int credits) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.block = block;
        this.semester = semester;
        this.year = year;
        this.mark = mark;
        this.status = status;
        this.credits = credits;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
    
}
