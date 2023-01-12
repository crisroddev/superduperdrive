package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@NoArgsConstructor
@Service
public class AttributesResponseService {
  public String attributesResponse(boolean res, Model model, String service) {
    model.addAttribute("success", res);
    model.addAttribute("nav", "//home#nav-" + service);
    return "res";
  }
}
