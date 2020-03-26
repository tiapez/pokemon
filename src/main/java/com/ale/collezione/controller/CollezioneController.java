package com.ale.collezione.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ale.collezione.model.Carta_Utente;
import com.ale.collezione.service.CartaService;
import com.ale.collezione.service.CartaUtenteService;
import com.ale.collezione.utility.CartaDesc;
import com.ale.collezione.utility.Filtro;

public class CollezioneController {
	
	@Autowired
	private CartaService cs;
	@Autowired
	private CartaUtenteService cus;
	
	/*// PAGINE CHE EFFETTIVAMENTE VENGONO VISUALIZZATE
		@RequestMapping(value = "/collezione/{nomeSet}", method = RequestMethod.GET)
		public ModelAndView collezioneSet(HttpSession session, @PathVariable String nomeSet) {

			ModelAndView mv = new ModelAndView();

			initSet();

			String codiceSet = set.get(nomeSet.toUpperCase());

			mv.setViewName("collezioneSet");

			List<Carta_Utente> listaSet = new ArrayList<Carta_Utente>();

			if (session.getAttribute("filtro") == null) {
				listaSet = cus.findBySetUser(codiceSet + "%", "1");

			} else {
				listaSet = cus.findBySetUserFiltered(codiceSet + "%", "1", (Filtro) session.getAttribute("filtro"));
				mv.addObject("filtro", (Filtro) session.getAttribute("filtro"));
			}

			mv.addObject("listaSet", listaSet);
			List<CartaDesc[]> listaCarte = creaListaCartaDesc(listaSet);
			mv.addObject("listaCarte", listaCarte);
			mv.addObject("nomeSet", nomeSet);
			for(CartaDesc carta[] : listaCarte) {
				System.out.println(carta[0].getId());
				System.out.println(carta[1].getId());
			}
			return mv;

		}
		
		@RequestMapping(value = "/filterCollezione", method = RequestMethod.POST)
		public ModelAndView inizializFiltroCollezione(HttpSession session,
				@RequestParam(value = "lingua", required = false) String lingua,
				@RequestParam(value = "rarita", required = false) String rarita,
				@RequestParam(value = "cond", required = false) String cond,
				@RequestParam(value = "set", required = true) String set) {
			Filtro filtro;
			if (session.getAttribute("filtro") == null) {
				filtro = new Filtro();
			} else {
				filtro = (Filtro) session.getAttribute("filtro");
			}
			if (lingua != null)
				filtro.setLingua(lingua);
			if (rarita != null)
				filtro.setRarita(rarita);
			if (cond != null)
				filtro.setCondizione(cond);
			session.setAttribute("filtro", filtro);
			System.out.println(filtro);
			return new ModelAndView("redirect:/collezione/" + set);

		}
		
		public List<CartaDesc[]> creaListaCartaDesc(List<Carta_Utente> list) {

			List<CartaDesc[]> listaCarte = new ArrayList<CartaDesc[]>();
			int contIta = 1;
			int contEng = 1;
			CartaDesc[] carta = new CartaDesc[2];
			carta[0] = new CartaDesc();
			carta[1] = new CartaDesc();
			
			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					if (list.get(i).getCarta().equals(list.get(i - 1).getCarta())) {
						if (list.get(i).getLingua().toUpperCase().equals("ITA")) {
							switch (contIta) {
							case 1:
								carta[0].setNome(list.get(i).getCarta());
								carta[0].setId(list.get(i).getCarta());
								carta[0].setCondizione1(nomeCondizione(list.get(i).getCondizione()));
								carta[0].setQta1(list.get(i).getQta());
								contIta++;
								break;
							case 2:
								carta[0].setCondizione2(nomeCondizione(list.get(i).getCondizione()));
								carta[0].setQta2(list.get(i).getQta());
								contIta++;
								break;
							case 3:
								carta[0].setCondizione3(nomeCondizione(list.get(i).getCondizione()));
								carta[0].setQta3(list.get(i).getQta());
								contIta++;
								break;
							case 4:
								carta[0].setCondizione4(nomeCondizione(list.get(i).getCondizione()));
								carta[0].setQta4(list.get(i).getQta());
								contIta++;
								break;
							case 5:
								carta[0].setCondizione5(nomeCondizione(list.get(i).getCondizione()));
								carta[0].setQta5(list.get(i).getQta());
								contIta++;
								break;
							}
						} else {
							switch (contEng) {						
							case 1:
								carta[1].setNome(list.get(i).getCarta());
								carta[1].setId(list.get(i).getCarta());
								carta[1].setCondizione1(nomeCondizione(list.get(i).getCondizione()));
								carta[1].setQta1(list.get(i).getQta());
								contEng++;
								break;
							case 2:
								carta[1].setCondizione2(nomeCondizione(list.get(i).getCondizione()));
								carta[1].setQta2(list.get(i).getQta());
								contEng++;
								break;
							case 3:
								carta[1].setCondizione3(nomeCondizione(list.get(i).getCondizione()));
								carta[1].setQta3(list.get(i).getQta());
								contEng++;
								break;
							case 4:
								carta[1].setCondizione4(nomeCondizione(list.get(i).getCondizione()));
								carta[1].setQta4(list.get(i).getQta());
								contEng++;
								break;
							case 5:
								carta[1].setCondizione5(nomeCondizione(list.get(i).getCondizione()));
								carta[1].setQta5(list.get(i).getQta());
								contEng++;
								break;
							}

						}
					} else {
						contIta = 1;
						contEng = 1;
						listaCarte.add(carta);
						carta = new CartaDesc[2];
						carta[0] = new CartaDesc();
						carta[1] = new CartaDesc();
						if (list.get(i).getLingua().toUpperCase().equals("ITA")) {
							carta[0].setNome(list.get(i).getCarta());
							carta[0].setId(list.get(i).getCarta());
							carta[0].setCondizione1(nomeCondizione(list.get(i).getCondizione()));
							carta[0].setQta1(list.get(i).getQta());
							contIta++;
						}
						
					 else {
						carta[1].setNome(list.get(i).getCarta());
						carta[1].setId(list.get(i).getCarta());
						carta[1].setCondizione1(nomeCondizione(list.get(i).getCondizione()));
						carta[1].setQta1(list.get(i).getQta());
						contEng++;
					}
				}

				}else {
					if (list.get(i).getLingua().toUpperCase().equals("ITA")) {
						carta[0].setNome(list.get(i).getCarta());
						carta[0].setId(list.get(i).getCarta());
						carta[0].setCondizione1(nomeCondizione(list.get(i).getCondizione()));
						carta[0].setQta1(list.get(i).getQta());
						contIta++;
					} else {
						carta[1].setNome(list.get(i).getCarta());
						carta[1].setId(list.get(i).getCarta());
						carta[1].setCondizione1(nomeCondizione(list.get(i).getCondizione()));
						carta[1].setQta1(list.get(i).getQta());
						contEng++;
					}
				}
			}
			if (list.size() == 1) {
				listaCarte.add(carta);
			}

			return listaCarte;

		}*/

}
