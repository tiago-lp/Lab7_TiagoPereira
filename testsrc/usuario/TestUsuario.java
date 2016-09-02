package usuario;

import static org.junit.Assert.*;

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
	public void registraJogada() throws Exception{
 
		Jogo kof = new Luta("The King Of Fighters 98", 120.0);
		Jogo ff = new Rpg("Final Fantasy VIII", 50.00);
		Jogo mario = new Plataforma("Super Mario", 20.0);
 
		arthur.adicionaDinheiro(300);
		arthur.compraJogo(kof);
 
 
		tiago.adicionaDinheiro(200);
		tiago.compraJogo(ff);
 
		neto.adicionaDinheiro(100);
		neto.compraJogo(mario);
 
	}
		/*try{
			arthur.re("The King Of Fighters 98", 200, false);
			assertEquals(2800, arthur.getX2p());
 
			arthur.registraJogada("The King Of Fighters 98", 2000, true);
			assertEquals(2802, arthur.getX2p());
 
			arthur.registraJogada("The King Of Fighters 98", 10000, false);
			assertEquals(2812, arthur.getX2p());
 
		}catch(Exception e){
			Assert.fail();
		}
 
 
		try{
			tiago.registraJogada("Final Fantasy VIII", 200, false);
			assertEquals(510, tiago.getX2p());
 
			tiago.registraJogada("Final Fantasy VIII", 3000, true);
			assertEquals(520, tiago.getX2p());
 
		}catch (Exception e){
			System.out.println(e.getMessage());
			Assert.fail();
		}
 
 
		try{
			neto.registraJogada("Super Mario", 500, false);
			assertEquals(1300, neto.getX2p());
 
			neto.registraJogada("Super Mario", 3000, true);
			assertEquals(1320, neto.getX2p());
 
		}catch(Exception e){
			Assert.fail();
		}
 
	}
	
	@Test
	public void registraJogadaException(){
		try{
			tiago.registraJogada("Tomb Raider", 5000, true);
		}catch(Exception e){
			Assert.assertEquals("Usuario nao possui esse jogo.", e.getMessage());
		}
	}*/
 
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
