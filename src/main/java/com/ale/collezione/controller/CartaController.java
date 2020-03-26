package com.ale.collezione.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.ale.collezione.utility.CartaDesc;
import com.ale.collezione.utility.Filtro;

@Controller
public class CartaController {
/*
	@Autowired
	private CartaService cs;
	@Autowired
	private CartaUtenteService cus;

	HashMap<String, String> set = new HashMap<String, String>();

	public void initSet() {
		if (set.isEmpty()) {
			set.put("SET_BASE", "01");
			set.put("JUNGLE", "02");
			set.put("FOSSIL", "03");
			set.put("ROCKET", "04");
		}
	}

	// PAGINE CHE EFFETTIVAMENTE VENGONO VISUALIZZATE
	@RequestMapping(value = "/aggiungi/{nomeSet}", method = RequestMethod.GET)
	public ModelAndView aggiungiCarteSet(HttpSession session, @PathVariable String nomeSet) {

		ModelAndView mv = new ModelAndView();

		initSet();

		String codiceSet = set.get(nomeSet.toUpperCase());

		mv.setViewName("home2");

		List<Carta> listaSet = new ArrayList<Carta>();

		if (session.getAttribute("filtro") == null) {
			listaSet = cs.findBySet(codiceSet + "%");
		} else {
			listaSet = cs.cardFiltered(codiceSet + "%", (Filtro) session.getAttribute("filtro"));
			mv.addObject("filtro", (Filtro) session.getAttribute("filtro"));
		}

		mv.addObject("listaSet", listaSet);
		mv.addObject("nomeSet", nomeSet);

		if (session.getAttribute("listaCarte") != null) {
			mv.addObject("listaCarte", session.getAttribute("listaCarte"));
		}

		return mv;

	}

	// PAGINE CHE EFFETTIVAMENTE VENGONO VISUALIZZATE
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

	// PAGINE CHE EFFETTIVAMENTE VENGONO VISUALIZZATE
	@RequestMapping(value = "/aggiungi/{nomeSet}", method = RequestMethod.POST)
	public ModelAndView aggiungiCarta2(HttpSession session, @PathVariable String nomeSet,
			@RequestParam(value = "idCarta") String idCarta, @RequestParam("cond") String cond,
			@RequestParam("nomeCarta") String nomeCarta) {
		// creo la carta
		String[] card = new String[7];
		card[0] = idCarta;
		card[1] = cond;
		if (session.getAttribute("filtro") == null) {
			card[2] = "ita";
		} else {
			card[2] = ((Filtro) session.getAttribute("filtro")).getLingua();
		}
		card[3] = "1"; // utente
		card[4] = nomeCarta;
		card[5] = nomeCondizione(cond);
		card[6] = "1"; // qta
		ArrayList<String[]> listaCarte = new ArrayList<String[]>();

		// CONTROLLO SE STO INSERENDO PIù COPIE DI UNA CARTA
		if (session.getAttribute("listaCarte") != null) {
			listaCarte = (ArrayList<String[]>) session.getAttribute("listaCarte");
			boolean flag = false;
			for (String[] carta : listaCarte) {
				if (card[0].equals(carta[0]) && card[1].equals(carta[1])) {
					int qta = Integer.parseInt(carta[6]);
					qta++;
					carta[6] = String.valueOf(qta);
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

		return new ModelAndView("redirect:/aggiungi/" + nomeSet);

	}

	// METODI
	// METODO PER AGGIUNGERE LE CARTE ALLA LISTA DI CARTE DA INSERIRE NEL DB
	@RequestMapping(value = "/addLista", method = RequestMethod.POST)
	public ModelAndView aggiungiCarta(@RequestParam("idCarta") String idCarta, @RequestParam("cond") String cond,
			@RequestParam("nomeCarta") String nomeCarta, HttpSession session) {
		// creo la carta
		String[] card = new String[7];
		card[0] = idCarta;
		card[1] = cond;
		if (session.getAttribute("filtro") == null) {
			card[2] = "ita";
		} else {
			card[2] = ((Filtro) session.getAttribute("filtro")).getLingua();
		}
		card[3] = "1"; // utente
		card[4] = nomeCarta;
		card[5] = nomeCondizione(cond);
		card[6] = "1"; // qta
		ArrayList<String[]> listaCarte = new ArrayList<String[]>();

		// CONTROLLO SE STO INSERENDO PIù COPIE DI UNA CARTA
		if (session.getAttribute("listaCarte") != null) {
			listaCarte = (ArrayList<String[]>) session.getAttribute("listaCarte");
			boolean flag = false;
			for (String[] carta : listaCarte) {
				if (card[0].equals(carta[0]) && card[1].equals(carta[1])) {
					int qta = Integer.parseInt(carta[6]);
					qta++;
					carta[6] = String.valueOf(qta);
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

		return new ModelAndView("redirect:/");
	}

	private String nomeCondizione(String cond) {
		String nomeCond = null;
		switch (Integer.parseInt(cond)) {
		case 1:
			nomeCond = "Mint";
			break;
		case 2:
			nomeCond = "Exc";
			break;
		case 3:
			nomeCond = "Good";
			break;
		case 4:
			nomeCond = "LightPlayed";
			break;
		case 5:
			nomeCond = "Played";
			break;
		}
		return nomeCond;
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public ModelAndView clear(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
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

	@RequestMapping(value = "/resetFilter", method = RequestMethod.GET)
	public ModelAndView resetFiltro(HttpSession session) {
		session.removeAttribute("filtro");
		return new ModelAndView("redirect:/");
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

	}

	// DBCONTROLLER
	@RequestMapping(value = "/addDB", method = RequestMethod.POST)
	public ModelAndView aggiungiCarta(HttpSession session) {

		if (session.getAttribute("listaCarte") != null) {
			ArrayList<String[]> listaCarte = (ArrayList<String[]>) session.getAttribute("listaCarte");
			List<Carta_Utente> listaUtente = cus.findBySetUser("01%", "1");

			for (String[] carta : listaCarte) {
				boolean flag = false;

				for (Carta_Utente cartaU : listaUtente) {
					System.out.println("qua");
					if (cartaU.getCarta().equals(carta[0]) && cartaU.getCondizione().equals(carta[1])
							&& cartaU.getLingua().equals(carta[2])) {
						System.out.println("qua2");
						cartaU.setQta(cartaU.getQta() + 1);
						cus.Save(cartaU);
						flag = true;
						break;
					}
				}
				if (!flag) {
					Carta_Utente card = new Carta_Utente();
					card.setCarta(carta[0]);
					card.setCondizione(carta[1]);
					card.setLingua(carta[2]);
					card.setUtente("1");
					card.setQta(Integer.parseInt(carta[6]));
					System.out.println(card);
					cus.Save(card);
				}
			}

		}

		return new ModelAndView("redirect:/clear");
	}
*/
	
	@RequestMapping(value = "/resetFilter", method = RequestMethod.GET)
	public ModelAndView resetFiltro(HttpSession session) {
		session.removeAttribute("filtro");
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public ModelAndView clear(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
}
