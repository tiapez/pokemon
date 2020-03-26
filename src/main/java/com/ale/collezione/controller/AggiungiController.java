package com.ale.collezione.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ale.collezione.model.Carta;
import com.ale.collezione.model.Carta_Utente;
import com.ale.collezione.service.CartaService;
import com.ale.collezione.service.CartaUtenteService;
import com.ale.collezione.utility.CarteUtility;
import com.ale.collezione.utility.DTOCartaDaAggiungere;
import com.ale.collezione.utility.Filtro;

@Controller
public class AggiungiController {

	@Autowired
	private CartaService cs;
	@Autowired
	private CartaUtenteService cus;
	@Autowired
	private CarteUtility carteUtility;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

	// PAGINE CHE EFFETTIVAMENTE VIENE VISUALIZZATA
	@RequestMapping(value = "/aggiungi/{nomeSet}", method = RequestMethod.GET)
	public ModelAndView aggiungiCarteSet(HttpSession session, @PathVariable String nomeSet) {

		ModelAndView mv = new ModelAndView();
		String codiceSet = carteUtility.codiceSet(nomeSet);

		mv.setViewName("home2");

		List<Carta> listaSet = new ArrayList<Carta>();

		if (session.getAttribute("filtro") == null) {
			listaSet = cs.findBySet(codiceSet + "%");
		} else {
			listaSet = cs.cardFiltered(codiceSet + "%", (Filtro) session.getAttribute("filtro"));
			mv.addObject("filtro", (Filtro) session.getAttribute("filtro"));
		}

		mv.addObject("listaSet", listaSet);
		mv.addObject("nomeSet", nomeSet.toUpperCase());

		if (session.getAttribute("listaCarte") != null) {
			mv.addObject("listaCarte", session.getAttribute("listaCarte"));
		}

		return mv;

	}

	//AGGIUNGO UN ELEMENTO ALLA LISTA
	@RequestMapping(value = "/aggiungi/{nomeSet}", method = RequestMethod.POST)
	public ModelAndView aggiungiListaCarta(HttpSession session, @PathVariable String nomeSet,
			@RequestParam(value = "idCarta") String idCarta, @RequestParam("cond") String cond,
			@RequestParam("nomeCarta") String nomeCarta) {
		// creo la carta
		DTOCartaDaAggiungere card = new DTOCartaDaAggiungere();
		card.setIdCarta(idCarta) ;
		card.setCond(cond);
		if (session.getAttribute("filtro") == null) {
			card.setLingua("ITA");
		} else {
			card.setLingua(((Filtro) session.getAttribute("filtro")).getLingua().toUpperCase());
		}
		card.setUsername("1"); // utente
		card.setNomeCarta(nomeCarta);
		card.setNomeCondizione(carteUtility.nomeCondizione(cond));
		card.setQta(1);
		ArrayList<DTOCartaDaAggiungere> listaCarte = new ArrayList<DTOCartaDaAggiungere>();

		// CONTROLLO SE STO INSERENDO PIù COPIE DI UNA CARTA
		if (session.getAttribute("listaCarte") != null) {
			listaCarte = (ArrayList<DTOCartaDaAggiungere>) session.getAttribute("listaCarte");
			boolean flag = false;
			for (DTOCartaDaAggiungere carta : listaCarte) {
				if (card.getIdCarta().equals(carta.getIdCarta()) && card.getCond().equals(carta.getCond()) && card.getLingua().equals(carta.getLingua())) {
					carta.setQta(carta.getQta()+1);
					flag = true;
					break;
				}

			}
			if (!flag) {
				listaCarte.add(card);
			}

		} else {
			listaCarte.add(card);
			session.setAttribute("listaCarte", listaCarte);
		}
		System.out.println(listaCarte);
		return new ModelAndView("redirect:/aggiungi/" + nomeSet);
	}

	//ELIMINO UN ELEMENTO DALLA LISTA
	@RequestMapping(value = "/eliminaDallaLista", method = RequestMethod.POST)
	public ModelAndView eliminaListaCarta(HttpSession session, @RequestParam String set2, @RequestParam int i) {
		ArrayList<DTOCartaDaAggiungere> list = (ArrayList<DTOCartaDaAggiungere>) session.getAttribute("listaCarte");
		DTOCartaDaAggiungere carta = list.get(i);
		System.out.println(set2);
		if(carta.getQta()>1)
			carta.setQta(carta.getQta()-1);
		else
			list.remove(i);
		
		return new ModelAndView("redirect:/aggiungi/" + set2);
	}

	// DBCONTROLLER
	@RequestMapping(value = "/addDB", method = RequestMethod.POST)
	public ModelAndView aggiungiCarta(HttpSession session) {

		if (session.getAttribute("listaCarte") != null) {
			ArrayList<DTOCartaDaAggiungere> listaCarte = (ArrayList<DTOCartaDaAggiungere>) session.getAttribute("listaCarte");

			for(DTOCartaDaAggiungere carta : listaCarte) {
				Carta_Utente card = cus.findByCartaUser(carta.getIdCarta(), "1", carta.getLingua(), carta.getCond());
				System.out.println(card);
				if(card != null) {
					card.setQta(card.getQta()+carta.getQta());
					cus.Save(card);
				}
				else {
					cus.Save(carta.getCartaUtente());
				}
				
					System.out.println(carta);
			}
		}

		return new ModelAndView("redirect:/clear");
	}

	//FILTRO
	@RequestMapping(value = "/aggiungifiltro", method = RequestMethod.POST)
	public ModelAndView inizializFiltro(HttpSession session,
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
		return new ModelAndView("redirect:/aggiungi/" + set);

	}

}
