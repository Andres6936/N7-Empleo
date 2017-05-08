/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: AspiranteTest.java,v 1.7 2007/04/12 03:43:58 carl-veg Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_empleo
 * Autor: Milena Vela - 21-abr-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.empleo.test;

import junit.framework.TestCase;
import uniandes.cupi2.empleo.mundo.Aspirante;

/**
 * Esta es la clase usada para verificar los m�todos de la clase Aspirante
 */
public class AspiranteTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Aspirante usado para los casos de prueba
     */
    private Aspirante aspirante1;

    /**
     * Aspirante usado para los casos de prueba
     */
    private Aspirante aspirante2;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye los aspirantes 1 y 2
     */
    private void setupEscenario1( )
    {
        aspirante1 = new Aspirante( "nombre1", Aspirante.ADMINISTRADOR , 10, 35, "1234567", "imagen1" );
        aspirante2 = new Aspirante( "nombre2", Aspirante.CONTADOR , 11, 40, "987654", "imagen2" );
    }

    /**
     * Verifica el constructor. <br>
     * <b> M�todos a probar: </b> <br>
     * Aspirante (constructor). <br>
     * <b> Objetivo: </b> Probar que el constructor crea un aspirante de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al crear un aspirante los atributos del objeto deben quedar con el valor correcto.
     */

    public void testAspirante( )
    {
        setupEscenario1( );

        assertEquals( "El nombre del aspirante est� equivocado", "nombre1", aspirante1.darNombre( ) );
        assertEquals( "La profesi�n del aspirante est� equivocada", Aspirante.ADMINISTRADOR, aspirante1.darProfesion( ) );
        assertEquals( "Los a�os de experiencia no son correctos", 10, aspirante1.darAniosExperiencia( ) );
        assertEquals( "La edad del aspirante no es correcta", 35, aspirante1.darEdad( ) );
        assertEquals( "El tel�fono del aspirante est� equivocado", "1234567", aspirante1.darTelefono( ) );
        assertEquals( "La imagen del aspirante est� equivocada", "imagen1", aspirante1.darImagen( ) );
    }

    /**
     * Verifica el m�todo compararPorNombre. <br>
     * <b> M�todos a probar: </b> <br>
     * compararPorNombre. <br>
     * <b> Objetivo: </b> Probar que el m�todo compararPorNombre realiza la comparaci�n de dos aspirantes de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar un aspirante cuyo nombre sea menor lexicogr�ficamente a la del otro el resultado debe ser -1. <br>
     * 2. Al comparar un aspirante cuyo nombre sea igual lexicogr�ficamente a la del otro el resultado debe ser 0. <br>
     * 3. Al comparar un aspirante cuyo nombre sea mayor lexicogr�ficamente a la del otro el resultado debe ser 1.
     */
    public void testCompararPorNombre( )
    {
        setupEscenario1( );

        assertEquals( "El aspirante 1 deber�a ser menor", -1, aspirante1.compararPorNombre( aspirante2 ) );
        assertEquals( "El aspirante 1 y el aspirante 1 deber�an ser iguales", 0, aspirante1.compararPorNombre( aspirante1 ) );
        assertEquals( "El aspirante 2 deber�a ser mayor", 1, aspirante2.compararPorNombre( aspirante1 ) );
    }

    /**
     * Verifica el m�todo compararPorAniosExperiencia. <br>
     * <b> M�todos a probar: </b> <br>
     * compararPorAniosExperiencia. <br>
     * <b> Objetivo: </b> Probar que el m�todo compararPorAniosExperiencia realiza la comparaci�n de dos aspirantes de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar un aspirante cuyos a�os de experiencia sean menores a los de otro el resultado debe ser -1. <br>
     * 2. Al comparar un aspirante cuyos a�os de experiencia sean iguales a los de otro el resultado debe ser 0. <br>
     * 3. Al comparar un aspirante cuyos a�os de experiencia sean mayores a los de otro el resultado debe ser 1.
     */

    public void testCompararPorAniosExperiencia( )
    {
        setupEscenario1( );

        assertEquals( "El aspirante 1 deber�a ser menor", -1, aspirante1.compararPorAniosExperiencia( aspirante2 ) );
        assertEquals( "El aspirante 1 y el aspirante 1 deber�an ser iguales", 0, aspirante1.compararPorAniosExperiencia( aspirante1 ) );
        assertEquals( "El aspirante 2 deber�a ser mayor", 1, aspirante2.compararPorAniosExperiencia( aspirante1 ) );
    }

    /**
     * Verifica el m�todo compararPorEdad. <br>
     * <b> M�todos a probar: </b> <br>
     * compararPorEdad. <br>
     * <b> Objetivo: </b> Probar que el m�todo compararPorEdad realiza la comparaci�n de dos aspirantes de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar un aspirante cuya edad sea menor a la del otro el resultado debe ser -1. <br>
     * 2. Al comparar un aspirante cuya edad sea igual a la del otro el resultado debe ser 0. <br>
     * 3. Al comparar un aspirante cuya edad sea mayor a la del otro el resultado debe ser 1.
     */
    public void testCompararPorEdad( )
    {
        setupEscenario1( );

        assertEquals( "El aspirante 1 deber�a ser menor", -1, aspirante1.compararPorEdad( aspirante2 ) );
        assertEquals( "El aspirante 1 y el aspirante 1 deber�an ser iguales", 0, aspirante1.compararPorEdad( aspirante1 ) );
        assertEquals( "El aspirante 2 deber�a ser mayor", 1, aspirante2.compararPorEdad( aspirante1 ) );
    }

    /**
     * Verifica el m�todo compararPorProfesion. <br>
     * <b> M�todos a probar: </b> <br>
     * compararPorProfesion. <br>
     * <b> Objetivo: </b> Probar que el m�todo compararPorProfesion realiza la comparaci�n de dos aspirantes de forma correcta. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al comparar un aspirante cuya profesi�n sea menor lexicogr�ficamente a la del otro el resultado debe ser -1. <br>
     * 2. Al comparar un aspirante cuya profesi�n sea igual lexicogr�ficamente a la del otro el resultado debe ser 0. <br>
     * 3. Al comparar un aspirante cuya profesi�n sea mayor lexicogr�ficamente a la del otro el resultado debe ser 1.
     */
    public void testCompararPorProfesion( )
    {
        setupEscenario1( );

        assertEquals( "El aspirante 1 deber�a ser menor", - 1, aspirante1.compararPorProfesion( aspirante2 ) );
        assertEquals( "El aspirante 1 y el aspirante 1 deber�an ser iguales", 0, aspirante1.compararPorProfesion( aspirante1 ) );
        assertEquals( "El aspirante 2 deber�a ser mayor", 1, aspirante2.compararPorProfesion( aspirante1 ) );
    }

}