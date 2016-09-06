package usuario;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import jogo.*;

public class TestUsuario {

	private Usuario tiago;
	private Usuario neto;
	private Usuario arthur;

	@Before
	public void setUp() throws Exception {
		this.tiago = new Usuario("Tiago Pereira","tiagolp");
		this.neto = new Usuario("Francisco Neto", "fgoneto");
		this.arthur = new Usuario("Arthur Sampaio", "arthurspc");
		neto.setX2p(1000);
		arthur.setX2p(1000);
		neto.upgrade();
		arthur.upgrade();
	}
 
	@Test
	public void testeUsuario() throws Exception{
		try{
			tiago  = new Usuario("Tiago Pereira","tiaglp");
			neto = new Usuario("Francisco Neto", "fgoneto");
		}catch (Exception e){
			fail(); //Nao deve lancar excecao aqui
		}
	}
 
	@Test
	public void testeConstrutor(){
 
		// nomes
		try {
			Usuario user = new Usuario("  ", "tiagolp");
 
		} catch (Exception e) {
			Assert.assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
 
		try {
			Usuario user = new Usuario(null, "tiagolp");
 
		} catch (Exception e) {
			Assert.assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
		
		try {
			Usuario user2 = new Usuario(" ", "fgoneto");
		} catch(Exception e) {
			Assert.assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
		
		try {
			Usuario user2 = new Usuario(null, "fgoneto");
		} catch(Exception e){
			Assert.assertEquals("Nome nao pode ser nulo ou vazio.", e.getMessage());
		}
 
		
		// logins
		try {
			Usuario user = new Usuario("Tiago Pereira", " ");
 
		} catch(Exception e) {
			Assert.assertEquals("Login nao pode ser nulo ou vazio.", e.getMessage());
		}
 
		try {
			Usuario user = new Usuario("Tiago Pereira", null);
 
		} catch (Exception e) {
			Assert.assertEquals("Login nao pode ser nulo ou vazio.", e.getMessage());
		}
 
 
		try {
			Usuario user = new Usuario("Neto", " ");
 
		} catch (Exception e) {
			Assert.assertEquals("Login nao pode ser nulo ou vazio.", e.getMessage());
		}
 
		try {
			Usuario user = new Usuario("Neto", null);
 
		} catch (Exception e) {
			Assert.assertEquals("Login nao pode ser nulo ou vazio.", e.getMessage());
		}
 
	}
 
	@Test
	public void addDinheiro() {
 
		try {
			tiago.adicionaDinheiro(1000.0);
			assertEquals(1000.0, tiago.getCredito(), 000.1);
			tiago.adicionaDinheiro(50);
			assertEquals(1050, tiago.getCredito(), 000.1);
		} catch (Exception e) {
			fail(); //Nao deve lancar excecao aqui
 
		}
	}
 
	@Test
	public void addDinheiroException() throws Exception{
		try{
			neto.adicionaDinheiro(-20);
 
		}catch (Exception e){
			Assert.assertEquals("Valor que deseja adicionar ao saldo nao pode ser negativo.", e.getMessage());
		}
	}
 
	@Test
	public void compraJogo() throws Exception{
 
		Jogo mario = new Plataforma("Super Mario", 20);
 
		try{	
 
			tiago.adicionaDinheiro(1000.0);
			tiago.compraJogo(mario);
			assertEquals(1,tiago.getMeusJogos().size());
			assertEquals(982.0, tiago.getCredito(), 00.1);
 
		}catch(Exception e){
			Assert.fail(); //Nao deve lancar excecao aqui
		}
 
		try{
			neto.adicionaDinheiro(1000);
			neto.compraJogo(mario);
			assertEquals(1,neto.getMeusJogos().size());
			assertEquals(984.0, neto.getCredito(), 00.1);
		}catch(Exception e){
			Assert.fail();//Nao deve lancar excecao aqui
		}
	}
 
	@Test
	public void compraJogoException() throws Exception{
 
		Jogo ff = new Rpg("Final Fantasy VIII", 50);
		
		try{
			tiago.compraJogo(ff);
			
		}catch (Exception e){
			Assert.assertEquals("Credito insuficiente para realizar a compra.", e.getMessage());
		}
 
 
		try{
	
			neto.adicionaDinheiro(120);
			neto.compraJogo(ff);
			neto.compraJogo(ff); 
 
		}catch (Exception e){
			Assert.assertEquals("Usuario ja possui o jogo.", e.getMessage());
 
		}
	}
 
	@Test
	public void recompensar() throws Exception{
		Set<Jogabilidade> jogabilidades1 = new HashSet<Jogabilidade>();
		Set<Jogabilidade> jogabilidades2 = new HashSet<Jogabilidade>();
	
		//noob
		jogabilidades1.add(Jogabilidade.OFFLINE);
		jogabilidades1.add(Jogabilidade.MULTIPLAYER);
		
		//veterano
		jogabilidades2.add(Jogabilidade.ONLINE);
		jogabilidades2.add(Jogabilidade.COOPERATIVO);


		
		Jogo tes1 = new Luta("test", 0.0, jogabilidades1);
		Jogo tes2 = new Luta("test2", 0.0, jogabilidades2);
	
		tiago.compraJogo(tes1);
		neto.compraJogo(tes2);
		
		tiago.recompensar("test", 0, true);
		//tiago eh noob
		Assert.assertEquals(40, tiago.getX2p());
		
		neto.recompensar("test2", 0, true);
		//neto eh veterano
		Assert.assertEquals(1030, neto.getX2p());
		
	}
 
	@Test
	public void punir() throws Exception{
		Set<Jogabilidade> jogabilidades1 = new HashSet<Jogabilidade>();
		Set<Jogabilidade> jogabilidades2 = new HashSet<Jogabilidade>();
	
		//noob
		jogabilidades1.add(Jogabilidade.ONLINE);
		jogabilidades1.add(Jogabilidade.COMPETITIVO);
		jogabilidades1.add(Jogabilidade.COOPERATIVO);
		
		//veterano
		jogabilidades2.add(Jogabilidade.COMPETITIVO);
		jogabilidades2.add(Jogabilidade.OFFLINE);
		
		Jogo tes1 = new Luta("test", 0.0, jogabilidades1);
		Jogo tes2 = new Luta("test2", 0.0, jogabilidades2);
	
		tiago.compraJogo(tes1);
		neto.compraJogo(tes2);
		
		tiago.setX2p(100);
		
		tiago.punir("test", 0, true);
		//tiago eh noob
		Assert.assertEquals(20, tiago.getX2p());
		
		neto.punir("test2", 0, true);
		//neto eh veterano
		Assert.assertEquals(960, neto.getX2p());
	}
	
	@Test
	public void testEquals() throws Exception{
 
		Usuario wesley = new Usuario("Wesley Anibal", "wesley.a");
		Usuario matheus = new Usuario("Wesley Anibal", "wesley.a");
		Usuario mariana = new Usuario("Mariana Mendes" ,"m4reana");
 
		assertTrue(wesley.equals(matheus));
		assertTrue(wesley.equals(wesley));
		assertFalse(mariana.equals(matheus));
		assertFalse(wesley.equals(mariana));
	}
}
