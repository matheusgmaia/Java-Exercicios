import java.util.ArrayList;

public class Usuario {

	// ATRIBUTOS
	private String nome;
	private String login;
	private double dinheiroQuePossui;
	int x2p = 0;
	double dinheiroGasto;
	TipoDeUsuario tipoDeUsuario;
	ArrayList<Jogo> jogosComprados = new ArrayList<Jogo>();

	// TIPOS DE USUARIO
	public enum TipoDeUsuario {
		NOOB(0.90, "Noob"), VETERANO(0.80, "Veterano");
		public double valorDoDesconto;
		public String tipoDeUsuario;

		TipoDeUsuario(double desconto, String tipo) {
			valorDoDesconto = desconto;
			tipoDeUsuario = tipo;
		}
	}

	public Usuario(String nome2, String login2, double grana) {
		this.setNome(nome2);
		this.login = login2;
		this.dinheiroQuePossui = grana;

	}

	public void comprarJogo(Jogo jogo) {
		Jogo jogoCopia = copiaJogo(jogo);
		this.jogosComprados.add(jogoCopia);
		this.x2p += jogo.valor * 10;
		this.dinheiroQuePossui -= jogo.valor
				* this.tipoDeUsuario.valorDoDesconto;
		this.dinheiroGasto += jogo.valor;
	}

	public void imprimeInformacoesUsuario() {
		System.out.println(this.login + "\n" + this.getNome() + " - Jogador"
				+ this.tipoDeUsuario.tipoDeUsuario + " X2p:" + this.x2p
				+ "\nLista de Jogos:");
		for (Jogo jogo : jogosComprados) {
			System.out.println("\n+ " + jogo.nome + "- " + jogo.tipo + ":"
					+ "\n==> Jogou " + jogo.vezesJogadas + " vez(es)"
					+ "\n==> Zerou " + jogo.vezesZeradas + " vez(es)"
					+ "\n==> Maior score: " + jogo.maiorScore
					+ "\n");
		}
		System.out.println("\nTotal de preco de jogos:" + this.dinheiroGasto 
				+ "\n-------------------------------\n");

	}

	public Jogo copiaJogo(Jogo original) {
		Jogo jogoNovo = null;
		if (original.tipo.equals(Jogo.TipoDeJogo.RPG)) {
			Jogo RPGNovo = new RPG(original.nome, original.valor,
					original.estilosDeJogo);
			jogoNovo = RPGNovo;
		} else if (original.tipo.equals(Jogo.TipoDeJogo.LUTA)) {
			Jogo LutaNovo = new Luta(original.nome, original.valor,
					original.estilosDeJogo);
			jogoNovo = LutaNovo;
		} else if (original.tipo.equals(Jogo.TipoDeJogo.PLATAFORMA)) {
			Jogo PlataformaNovo = new Plataforma(original.nome, original.valor,
					original.estilosDeJogo);
			jogoNovo = PlataformaNovo;
		}
		return jogoNovo;

	}

	public void recopensar(String nome, double score, boolean zerou) {
		int pontosPorJogar = 0;
		int recompensa = 0;
		for (Jogo jogo : jogosComprados) {
			if (jogo.nome == nome) {
				int x2pGanho = jogo.joga(score, zerou);
				if (jogo.tipo.equals(Jogo.TipoDeJogo.LUTA)) {
					if (jogo.maiorScore < score) {
						pontosPorJogar += x2pGanho;
					}
				} else {
					pontosPorJogar += x2pGanho;
				}
				for (Jogo.EstiloDeJogo estilo : jogo.estilosDeJogo) {
					if (this.tipoDeUsuario.equals(Usuario.TipoDeUsuario.NOOB)) {
						recompensa += estilo.recompensaNoob;
					} else {
						recompensa += estilo.recompensaVeterano;
					}
				}
				this.x2p += (pontosPorJogar + recompensa);
			}
		}

	}

	public void punir(String nome, double score, boolean zerou) {
		int pontosPorJogar = 0;
		int punicao = 0;
		for (Jogo jogo : jogosComprados) {
			if (jogo.nome == nome) {
				int x2pGanho = jogo.joga(score, zerou);
				if (jogo.tipo.equals(Jogo.TipoDeJogo.LUTA)) {
					if (jogo.maiorScore < score) {
						pontosPorJogar += x2pGanho;
					}
				} else {
					pontosPorJogar += x2pGanho;
				}
				for (Jogo.EstiloDeJogo estilo : jogo.estilosDeJogo) {
					if (this.tipoDeUsuario.equals(Usuario.TipoDeUsuario.NOOB)) {
						punicao -= estilo.recompensaNoob;
					} else {
						punicao -= estilo.recompensaVeterano;
					}
				}
				this.x2p += (pontosPorJogar - punicao);
			}
		}

	}

	public double getDinheiroQuePossui() {
		return dinheiroQuePossui;
	}

	public void setDinheiroQuePossui(double dinheiroQuePossui) {
		this.dinheiroQuePossui = dinheiroQuePossui;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void adicionaDinheiro(double valor) {
		setDinheiroQuePossui((getDinheiroQuePossui() + valor));
	}

	public int getX2p() {
		return x2p;
	}

	public void setX2p(int x2p) {
		this.x2p = x2p;
	}

	public double getDinheiroGasto() {
		return dinheiroGasto;
	}

	public void setDinheiroGasto(double dinheiroGasto) {
		this.dinheiroGasto = dinheiroGasto;
	}

	public TipoDeUsuario getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}

	public ArrayList<Jogo> getJogosComprados() {
		return jogosComprados;
	}

	public void setJogosComprados(ArrayList<Jogo> jogosComprados) {
		this.jogosComprados = jogosComprados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
