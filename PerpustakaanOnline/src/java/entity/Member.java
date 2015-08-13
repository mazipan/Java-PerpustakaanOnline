/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Administrator
 */
public class Member {

    private final Member member;
    private Integer memberId;
    private String memberPassword;

    private String memberNik;
    private String memberName;
    private String memberAlamat;
    private String memberTelp;
    private String memberExt;
    private String memberBagian;
    private String memberLokasi;
    private GeneralModel gmMember;

    public GeneralModel getGmMember() {
        gmMember = new GeneralModel(member.getMemberId(), member.getMemberName(), "Member", member);

        return gmMember;
    }

    
    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
    
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberNik() {
        return memberNik;
    }

    public void setMemberNik(String memberNik) {
        this.memberNik = memberNik;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAlamat() {
        return memberAlamat;
    }

    public void setMemberAlamat(String memberAlamat) {
        this.memberAlamat = memberAlamat;
    }

    public String getMemberTelp() {
        return memberTelp;
    }

    public void setMemberTelp(String memberTelp) {
        this.memberTelp = memberTelp;
    }

    public String getMemberExt() {
        return memberExt;
    }

    public void setMemberExt(String memberExt) {
        this.memberExt = memberExt;
    }

    public String getMemberBagian() {
        return memberBagian;
    }

    public void setMemberBagian(String memberBagian) {
        this.memberBagian = memberBagian;
    }

    public String getMemberLokasi() {
        return memberLokasi;
    }

    public void setMemberLokasi(String memberLokasi) {
        this.memberLokasi = memberLokasi;
    }

    @Override
    public String toString() {
        return "Member[memberId=" + memberId + ",memberNik=" + memberNik + ",memberName=" + memberName + ",memberAlamat=" + memberAlamat + ",memberBagian=" + memberBagian + ",memberLokasi=" + memberLokasi + "]";
    }

    public Member() {
        member = this;
    }
}
