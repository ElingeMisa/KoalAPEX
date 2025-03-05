/*/
package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarea_seq")
    @SequenceGenerator(name = "tarea_seq", sequenceName = "Tarea_seq", allocationSize = 1)
    @Column(name = "idTarea", nullable = false)
    private Integer idTarea;

    @Column(name = "Descripcion", length = 100)
    private String descripcion;

    @Column(name = "FechaEntrega")
    private LocalDateTime fechaEntrega;

    @Column(name = "Horas_Estimadas", columnDefinition = "NUMBER DEFAULT 2")
    private Integer horasEstimadas = 2;

    @Column(name = "Horas_Reales", length = 100)
    private String horasReales = "2";

    @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    @Column(name = "Estado", length = 100)
    private String estado = "Activo";

    @Column(name = "Categoria", length = 100)
    private String categoria = "Tarea";

    @ManyToOne
    @JoinColumn(name = "idDesarrollador", referencedColumnName = "idDesarrollador", nullable = true)
    private Desarrollador desarrollador;

    @ManyToOne
    @JoinColumn(name = "idProyecto", referencedColumnName = "idProyecto", nullable = true)
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "idSprint", referencedColumnName = "idSprint", nullable = true)
    private Sprint sprint;

    // Constructores
    public Tarea() {}

    public Tarea(Integer idTarea, String descripcion, LocalDateTime fechaEntrega, Integer horasEstimadas, String horasReales,
                 Integer activo, String estado, String categoria, Desarrollador desarrollador, Proyecto proyecto, Sprint sprint) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.horasEstimadas = horasEstimadas;
        this.horasReales = horasReales;
        this.activo = activo;
        this.estado = estado;
        this.categoria = categoria;
        this.desarrollador = desarrollador;
        this.proyecto = proyecto;
        this.sprint = sprint;
    }

    // Getters y Setters
    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(Integer horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public String getHorasReales() {
        return horasReales;
    }

    public void setHorasReales(String horasReales) {
        this.horasReales = horasReales;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Desarrollador getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(Desarrollador desarrollador) {
        this.desarrollador = desarrollador;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
*/