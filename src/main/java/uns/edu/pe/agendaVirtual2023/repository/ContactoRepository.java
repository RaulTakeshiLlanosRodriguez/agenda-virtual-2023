/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uns.edu.pe.agendaVirtual2023.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uns.edu.pe.agendaVirtual2023.model.Contacto;

/**
 *
 * @author USER
 */
@Repository
public interface ContactoRepository extends JpaRepository<Contacto,Integer>{

    //realizando funcion personalizada de busqueda por nombre
    Page<Contacto> findByNombreContaining(String nombre, Pageable pageable);
}
