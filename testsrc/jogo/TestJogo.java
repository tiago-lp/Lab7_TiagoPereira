package jogo;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import excecoes.*;

public class TestJogo {
	
	private Jogo kof; 				// luta
	private Jogo sonic; 			// plataforma
	private Jogo ff;		// rpg
	
	@Before
	public void setUp() throws Exception {
		
		kof = new Luta("The King Of Figthers", 12.00);
		sonic = new Plataforma("Sonic The Hedgehog", 15.00);
		ff = new Rpg("Final Fantasy VIII", 23.00);
		
	}
	
	@Test
	public void construtor(){
		try {
			Jogo castlevania = new Plataforma("Castlevania Symphony of the Night", 12.00);
			Jogo kingdonHearts = new Rpg("Kingdon Hearts", 15.00);
			Jogo StreetsOfRage  = new Rpg("Streets of Rage", 23.00);
		} catch (Exception e) {
			Assert.fail(); // nao deve lancar excecao aqui
		}
		
	}
	
	@Test
	public void construtorException(){
		try{
			Jogo exemplo  = new Rpg(" ", 250);

		}catch (LojaException e){
			Assert.assertEquals("Nome nao pode ser nulo ou vazio." , e.getMessage());
		}
 
 
		try{
			Jogo exemplo  = new Rpg(null, 250);

		}catch (LojaException e){
			Assert.assertEquals("Nome nao pode ser nulo ou vazio." , e.getMessage());
		}
 
 
		try{
			Jogo exemplo  = new Rpg("Killzone", -250);

		}catch (LojaException e){
			Assert.assertEquals("Preco nao pode ser negativo." , e.getMessage());
		}
	}
	
	@Test
	public void registraJogada(){
		Assert.assertEquals(0, sonic.getMaiorScore());
		Assert.assertEquals(0, sonic.getVezesJogadas());
		Assert.assertEquals(0, sonic.getvezesConcluidas());
		try{
			sonic.registraJogada(5000, true);

		}catch(Exception e){
			Assert.fail(); //nao ocorre excecao
		}
		
		Assert.assertEquals(5000, sonic.getMaiorScore());
		Assert.assertEquals(1, sonic.getVezesJogadas());
		Assert.assertEquals(1, sonic.getvezesConcluidas());
	}
	
	@Test
	public void testEquals(){
		try{
			Jogo sonic2 = new Plataforma("Sonic the Hedgehog", 15.00);
			Assert.assertTrue(sonic.equals(sonic2));
			Assert.assertFalse(sonic.equals(ff));
			
		}catch(Exception e){
			Assert.fail(); // nao deve ser lancado exception
		}
	}
}