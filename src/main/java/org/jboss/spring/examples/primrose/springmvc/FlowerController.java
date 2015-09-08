package org.jboss.spring.examples.primrose.springmvc;

import java.util.List;


import org.jboss.spring.examples.primrose.dao.FlowerDao;
import org.jboss.spring.examples.primrose.entity.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FlowerController
{
   @Autowired
   FlowerDao flowerService;

   @RequestMapping(value = "/searchFlowers.do")
   ModelAndView getAllFlowers()
   {
      List<Flower> flowers = flowerService.getAllFlowers();

      ModelAndView model = new ModelAndView("listFlowers");
      model.addObject(flowers);
      return model;
   }

   @RequestMapping(value = "/flowerDetail.do")
   ModelAndView getFlowerDetail(@RequestParam("id") long id)
   {
      Flower flower = flowerService.find(id);

      ModelAndView model = new ModelAndView("flowerDetail");
      model.addObject(flower);
      return model;
   }

   @RequestMapping(value = "/waterNow.do")
   ModelAndView waterNow(@RequestParam("id") long id)
   {
      Flower flower = flowerService.find(id);
      flower = flowerService.water(flower, 0.5d);

      ModelAndView model = new ModelAndView("flowerDetail");
      model.addObject(flower);
      return model;
   }
}
