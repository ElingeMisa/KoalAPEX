/*/
package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "Desarrollador")
public class Desarrollador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "desarrollador_seq")
    @SequenceGenerator(name = "desarrollador_seq", sequenceName = "Desarrollador_seq", allocationSize = 1)
    @Column(name = "idDesarrollador", nullable = false)
    private Integer idDesarrollador;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "ID", nullable = true)
    private Usuarios usuario;

    @Column(name = "Modalidad", length = 100)
    private String modalidad = "Presencial";

    @Column(name = "CodigoChamba", length = 100)
    private String codigoChamba = "0888880";

    @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    // Constructores
    public Desarrollador() {}

    public Desarrollador(Integer idDesarrollador, Usuarios usuario, String modalidad, String codigoChamba, Integer activo) {
        this.idDesarrollador = idDesarrollador;
        this.usuario = usuario;
        this.modalidad = modalidad;
        this.codigoChamba = codigoChamba;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(Integer idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCodigoChamba() {
        return codigoChamba;
    }

    public void setCodigoChamba(String codigoChamba) {
        this.codigoChamba = codigoChamba;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
*/
