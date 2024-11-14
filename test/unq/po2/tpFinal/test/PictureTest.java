package unq.po2.tpFinal.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.po2.tpFinal.domain.Picture;

public class PictureTest {

	private Picture picture;
	private String name;
	private String resourceUrl;

	@BeforeEach
	public void setUp() {
		name = "objetos.bmp";
		resourceUrl = "https://www.unq.edu.ar/objetos.jpg";
		picture = new Picture(name, resourceUrl);
	}

	@Test
	public void testGetName() {

		assertEquals(name, picture.getName());
	}

	@Test
	public void testGetResourceUrl() {
		assertEquals(resourceUrl, picture.getResourceUrl());
	}
}
