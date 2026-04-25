package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import uz.company.hrms.enums.PunishmentType;

import java.time.LocalDate;

@Entity
@Table(name = "punishments",
indexes = {
        @Index(name = "idx_punishment_employee",columnList = "employee_id"),
        @Index(name = "idx_punishment_date",columnList = "punishment_date"),
        @Index(name = "idx_punishment_type",columnList = "punishment_type"),
        @Index(name = "idx_punishment_active",columnList = "active")
})
public class Punishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * qaysi xodim jazolangan
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "employee_id",
    nullable = false,
    foreignKey = @ForeignKey(name = "fk_punishment_employee"))
    private Employee employee;

    /**
     * jazo turi
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "punishment_type",nullable = false,length = 50)
    private PunishmentType punishmentType;

    /**
     * jazo qo'llanilgan sana
     */
    @NotNull(message = "Punishment date is required")
    @Column(name = "punishment_date",nullable = false)
    private LocalDate punishmentDate;

    /**
     * eski jazo yoki yangi jazo ekanini elgilash uchun
     */
    @Column(name = "old_punishment",nullable = false)
    private boolean oldPunishment=false;

    /**
     * jazo failmi ?
     * true = hali yechilmagan
     * false = yechilgan
     */
    @Column(name = "active",nullable = false)
    private boolean active=false;

    /**
     * jazo yechilgan sana
     */
    @Column(name = "resolved_date")
    private LocalDate resolvedDate;

    public Punishment(){}

    public Punishment(Long id, Employee employee, PunishmentType punishmentType, LocalDate punishmentDate, boolean oldPunishment, boolean active, LocalDate resolvedDate) {
        this.id = id;
        this.employee = employee;
        this.punishmentType = punishmentType;
        this.punishmentDate = punishmentDate;
        this.oldPunishment = oldPunishment;
        this.active = active;
        this.resolvedDate = resolvedDate;
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PunishmentType getPunishmentType() {
        return punishmentType;
    }

    public void setPunishmentType(PunishmentType punishmentType) {
        this.punishmentType = punishmentType;
    }

    public LocalDate getPunishmentDate() {
        return punishmentDate;
    }

    public void setPunishmentDate(LocalDate punishmentDate) {
        this.punishmentDate = punishmentDate;
    }

    public boolean isOldPunishment() {
        return oldPunishment;
    }

    public void setOldPunishment(boolean oldPunishment) {
        this.oldPunishment = oldPunishment;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(LocalDate resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

}
