package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import uz.company.hrms.enums.Rank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees",uniqueConstraints = @UniqueConstraint(name = "uk_employee_personal_number",columnNames = "personal_number"))
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // F.I.O
    @NotBlank(message = "Full name is required")
    private String fullName;

    /**
     *     Unvon
     *     masalan: Leytenant , Kapitan
     */
    @NotNull(message = "Runk is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "rank",nullable = false,length = 50)
    private Rank rank;

    /**
     * Bo'lim
     */
    @NotNull(message = "Department is required")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "department_id",nullable = false,
    foreignKey = @ForeignKey(name = "fk_employee_department"))
    private Department department;


    /**
     * Lavozim
     */
    @NotNull(message = "Position is required")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "position_id",nullable = false,
    foreignKey = @ForeignKey(name = "fk_employee_position"))
    private Position position;

    /**
     * Tug'ulgan sana
     */
    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    /**
     * Manzil
     */
    @NotBlank(message = "Address is required")
    @Size(min = 2,max = 255,message = "Address must be between 2 and 255 characters")
    @Column(name = "address",nullable = false,length = 255)
    private String address;

    /**
     * Ishga kirgan sana
     * Shu sanadan boshlab staj hisoblanadi
     */
    @NotNull(message = "Employee date is required")
    @PastOrPresent(message = "Employee date cannot be in the future")
    @Column(name = "employment_date",nullable = false)
    private LocalDate employmentDate;

    /**
     * Egallab turgan unvoniga tayinlangan sana
     */
    @NotNull(message = "Rank assigned date is required")
    @PastOrPresent(message = "Rank assigned date cannot be in the future")
    @Column(name = "rank_assigned_date",nullable = false)
    private LocalDate rankAssignedDate;

    /**
     * Taqdirlangan ishhona / mukofotlar soni
     */
    @NotNull(message = "Award count is required")
    @PositiveOrZero(message = "Award count cannot be negative")
    @Column(name = "award_count",nullable = false)
    private Integer awardCountFromOffice=0;

    /**
     * Taqdirlanish Toshkent
     */
    @NotNull(message = "Appreciation count is required")
    @PositiveOrZero(message = "Appreciation count cannot be negative")
    @Column(name = "appreciation_count",nullable = false)
    private Integer appreciationCountFromTashkent=0;

    /**
     * Keyingi attestatiyaga borish sanasi
     */
    @NotNull(message = "Next attestation date is required")
    @Column(name = "next_attestation_date",nullable = false)
    private LocalDate nextAttestationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",unique = true,
    foreignKey = @ForeignKey(name = "fk_employee_user"))
    private User user;

    @OneToMany(mappedBy ="employee",fetch = FetchType.LAZY)
    private List<Document> documents=new ArrayList<>();

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<VacationSchedule> vacationSchedules=new ArrayList<>();

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<SickLeave> sickLeaves=new ArrayList<>();

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<Pensioner> pensioners=new ArrayList<>();

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<DismissedEmployee> dismissedEmployees=new ArrayList<>();

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<Award> awards=new ArrayList<>();

    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<Punishment> punishments=new ArrayList<>();

    public Employee(){}

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt=LocalDate.now();
    }

    public Employee(Long id, String fullName, Rank rank, Department department, Position position, LocalDate birthDate, String address, LocalDate employmentDate, LocalDate rankAssignedDate, Integer awardCountFromOffice, Integer appreciationCountFromTashkent, LocalDate nextAttestationDate, LocalDate createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.rank = rank;
        this.department = department;
        this.position = position;
        this.birthDate = birthDate;
        this.address = address;
        this.employmentDate = employmentDate;
        this.rankAssignedDate = rankAssignedDate;
        this.awardCountFromOffice = awardCountFromOffice;
        this.appreciationCountFromTashkent = appreciationCountFromTashkent;
        this.nextAttestationDate = nextAttestationDate;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDate getRankAssignedDate() {
        return rankAssignedDate;
    }

    public void setRankAssignedDate(LocalDate rankAssignedDate) {
        this.rankAssignedDate = rankAssignedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<VacationSchedule> getVacationSchedules() {
        return vacationSchedules;
    }

    public void setVacationSchedules(List<VacationSchedule> vacationSchedules) {
        this.vacationSchedules = vacationSchedules;
    }

    public List<SickLeave> getSickLeaves() {
        return sickLeaves;
    }

    public void setSickLeaves(List<SickLeave> sickLeaves) {
        this.sickLeaves = sickLeaves;
    }

    public List<Pensioner> getPensioners() {
        return pensioners;
    }

    public void setPensioners(List<Pensioner> pensioners) {
        this.pensioners = pensioners;
    }

    public List<DismissedEmployee> getDismissedEmployees() {
        return dismissedEmployees;
    }

    public void setDismissedEmployees(List<DismissedEmployee> dismissedEmployees) {
        this.dismissedEmployees = dismissedEmployees;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
    }

    public List<Punishment> getPunishments() {
        return punishments;
    }

    public void setPunishments(List<Punishment> punishments) {
        this.punishments = punishments;
    }

    public Integer getAwardCountFromOffice() {
        return awardCountFromOffice;
    }

    public void setAwardCountFromOffice(Integer awardCountFromOffice) {
        this.awardCountFromOffice = awardCountFromOffice;
    }

    public Integer getAppreciationCountFromTashkent() {
        return appreciationCountFromTashkent;
    }

    public void setAppreciationCountFromTashkent(Integer appreciationCountFromTashkent) {
        this.appreciationCountFromTashkent = appreciationCountFromTashkent;
    }

    public LocalDate getNextAttestationDate() {
        return nextAttestationDate;
    }

    public void setNextAttestationDate(LocalDate nextAttestationDate) {
        this.nextAttestationDate = nextAttestationDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }


}
