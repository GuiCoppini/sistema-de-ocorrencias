package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class OccurrenceController {

    @Autowired
    OccurrenceRepository occurrenceRepository;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/nova-ocorrencia")
    public ModelAndView newOccurrence(Occurrence oc) {
        ModelAndView mv =  new ModelAndView("occurrence_form");
        mv.addObject("occurrence", oc);
        return mv;
    }

    @RequestMapping("/ocorrencias")
    public ModelAndView occurrenceList() {
        ModelAndView mv = new ModelAndView("occurrence_list");
        mv.addObject("occurrences", occurrenceRepository.findAll());

        return mv;
    }

    @RequestMapping("/{id}/editar")
    public ModelAndView edit(@PathVariable("id") Long id) {
        Occurrence oc = occurrenceRepository.findOne(id);

        occurrenceRepository.delete(oc.getId());
        return newOccurrence(oc);
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {

        occurrenceRepository.delete(id);

        return new ModelAndView("redirect:/ocorrencias");
    }

    @PostMapping("/salvar")
    public ModelAndView save(@Valid Occurrence oc, BindingResult result) {
        if (result.hasErrors())
            return newOccurrence(oc);

        occurrenceRepository.save(oc);

        return new ModelAndView("redirect:/ocorrencias");

    }
}
