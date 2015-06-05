import java.util.*;

public class Loja {
	// DADOS
	private ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
	private ArrayList<Jogo> jogosCadastrados = new ArrayList<Jogo>();
	private double totalArrecadado = 0;
	Map<String, Usuario> mapaDeUsuarios = new HashMap<String, Usuario>();
	Map<String, Double> tabelaDeJogos = new HashMap<String, Double>();

	public void criaUsuario(String nome, String login, double grana) {
		Usuario usuarioNovo = new Noob(nome, login, grana);
		mapaDeUsuarios.put(login, usuarioNovo);
		usuariosCadastrados.add(usuarioNovo);
	}

	public void jogoNovo(String nome, double valor, Jogo.TipoDeJogo tipo,
			Jogo.EstiloDeJogo... deJogos) {
		Jogo jogoNovo = null;
		if (tipo.equals(Jogo.TipoDeJogo.RPG)) {
			Jogo RPGNovo = new RPG(nome, valor, deJogos);
			jogoNovo = RPGNovo;
		} else if (tipo.equals(Jogo.TipoDeJogo.LUTA)) {
			Jogo LutaNovo = new Luta(nome, valor, deJogos);
			jogoNovo = LutaNovo;
		} else if (tipo.equals(Jogo.TipoDeJogo.PLATAFORMA)) {
			Jogo PlataformaNovo = new Plataforma(nome, valor, deJogos);
			jogoNovo = PlataformaNovo;
		}
		jogosCadastrados.add(jogoNovo);
		tabelaDeJogos.put(jogoNovo.getNome(), jogoNovo.getValor());
	}

	public void vendaJogos(String nome, String login) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getLogin() == login) {
				if (tabelaDeJogos.containsKey(nome)) {
					if (usuario.getDinheiroQuePossui() >= tabelaDeJogos
							.get(nome)) {
						for (Jogo jogo : jogosCadastrados) {
							if (jogo.getNome() == nome) {
								totalArrecadado += (tabelaDeJogos.get(nome) * usuario.tipoDeUsuario.valorDoDesconto);
								usuario.comprarJogo(jogo);

							}
						}
					} else {
						System.out
								.println("Usuario não tem dinheiro suficiente");
					}
				} else {
					System.out.println("Jogo nao cadastrado!");

				}
			}
		}
	}

	public void adicionaDinheiro(String login, double valor) {
		if (mapaDeUsuarios.containsKey(login)) {
			for (Usuario usuario : usuariosCadastrados) {
				if (usuario.getLogin() == login) {
					usuario.adicionaDinheiro(valor);
				}
			}
		} else {
			System.out.println("Usuario não cadastrado");
		}
	}

	public void imprimirInformacoes() {
		System.out.println("======== Centra P2-CG ========\n");
		for (Usuario usuario : usuariosCadastrados) {
			usuario.imprimeInformacoesUsuario();
		}
		System.out
				.println("Total arrecadado com vendas de jogos: R$ "
						+ totalArrecadado);
	}

	public void upgrade(String id) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getLogin() == id) {
				if ((usuario.x2p >= 1000) && (usuario instanceof Noob)) {
					Veterano u = new Veterano(usuario.getNome(),
							usuario.getLogin(), usuario.getDinheiroQuePossui());
					u.x2p = usuario.x2p;
					u.dinheiroGasto = usuario.dinheiroGasto;
					u.jogosComprados = usuario.jogosComprados;
					usuariosCadastrados.remove(usuario);
					usuariosCadastrados.add(u);
				}
			}
		}
	}

	public void downgrade(String id) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getLogin() == id) {
				if ((usuario.x2p < 1000) && (usuario instanceof Veterano)) {
					Noob u = new Noob(usuario.getNome(), usuario.getLogin(),
							usuario.getDinheiroQuePossui());
					u.x2p = usuario.x2p;
					u.dinheiroGasto = usuario.dinheiroGasto;
					u.jogosComprados = usuario.jogosComprados;
					usuariosCadastrados.remove(usuario);
					usuariosCadastrados.add(u);
				}
			}
		}
	}

	public void recopensar(String id, String string2, int i, boolean b) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getLogin() == id) {
				usuario.recopensar(string2, i, b);
			}
		
	}

}

	public void punir(String id, String string2, int i, boolean b) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getLogin() == id) {
				usuario.recopensar(string2, i, b);
			}
		
	}		
	}
}
