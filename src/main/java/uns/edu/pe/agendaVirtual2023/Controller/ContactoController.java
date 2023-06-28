/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uns.edu.pe.agendaVirtual2023.Controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uns.edu.pe.agendaVirtual2023.model.Contacto;
import uns.edu.pe.agendaVirtual2023.repository.ContactoRepository;

/**
 *
 * @author USER
 */
@Controller
public class ContactoController {
    
    @Autowired
    private ContactoRepository contactoRepository;
    
    @GetMapping
    String index(@PageableDefault(size = 5, sort = "fechaRegistro", direction = Sort.Direction.DESC) Pageable pageable,@RequestParam(required = false) String busqueda, Model model){
        Page<Contacto> contactoPage;
        if(busqueda != null && busqueda.trim().length() > 0){
            contactoPage = contactoRepository.findByNombreContaining(busqueda, pageable);
        }else{
            contactoPage = contactoRepository.findAll(pageable);
        }
        model.addAttribute("contactos",contactoPage);
        return "index";
    }
    
    @GetMapping("/nuevo")
    String nuevo(Model model){
        model.addAttribute("contacto", new Contacto());
        return "nuevo";
    }
    
    @PostMapping("/nuevo")
    String crear(@Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasErrors()){
           model.addAttribute("contacto",contacto);
           return "nuevo";
        }
       contacto.setFechaRegistro(LocalDateTime.now());
       contactoRepository.save(contacto);
       redirectAttributes.addFlashAttribute("msgExito","El contacto se ha creado correctamente");
       return "redirect:/"; 
    }
    
    @GetMapping("/{id}/editar")
    String editar(@PathVariable Integer id, Model model){
        
        Contacto contacto = contactoRepository.getById(id);
        model.addAttribute("contacto", contacto);
        return "nuevo";
    }
    
    @PostMapping("/{id}/editar")
    String actualizar(@PathVariable Integer id, @Validated Contacto contacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        
       
        
        if(bindingResult.hasErrors()){
           model.addAttribute("contacto",contacto);
           return "nuevo";
        }
        
        Contacto contactoDB = contactoRepository.getById(id);
        contactoDB.setNombre(contacto.getNombre());
        contactoDB.setCelular(contacto.getCelular());
        contactoDB.setEmail(contacto.getEmail());
        contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());
        
       contactoRepository.save(contactoDB);
       redirectAttributes.addFlashAttribute("msgExito","El contacto se ha actualizado correctamente");
       return "redirect:/"; 
    }
    
    @PostMapping("/{id}/eliminar")
    String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes){
       Contacto contacto = contactoRepository.getById(id);
       contactoRepository.delete(contacto);
       redirectAttributes.addFlashAttribute("msgExito","El contacto se ha eliminado correctamente");
       return "redirect:/"; 
    }
}
