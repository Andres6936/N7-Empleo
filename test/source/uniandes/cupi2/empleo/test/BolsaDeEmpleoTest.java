/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BolsaDeEmpleoTest.java,v 1.8 2007/04/12 03:43:58 carl-veg Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_empleo
 * Autor: Milena Vela - 21-abr-2006
 * Modificaci�n: Silvia de la Torre -07-jul-2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.empleo.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.empleo.mundo.Aspirante;
import uniandes.cupi2.empleo.mundo.BolsaDeEmpleo;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase BolsaDeEmpleo est�n correctamente implementados
 */
public class BolsaDeEmpleoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la bolsa de empleo sobre la que se van a realizar las pruebas
     */
    private BolsaDeEmpleo bolsa;

    /**
     * La cantidad de aspirantes que hay en la bolsa de empleo.
     */
    private int cantidadAspirantes;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Crea un bolsa de empleo a partir del archivo aspirantes1.dat
     */
    private void setupEscenario1( )
    {
        bolsa = new BolsaDeEmpleo( );        
        
        cargarAspirantes( "./test/data/aspirantes1.dat" );

    }

    /**
     * Crea un bolsa de empleo a partir del archivo aspirantes2.dat
     */
    private void setupEscenario2( )
    {
        bolsa = new BolsaDeEmpleo( );
        cargarAspirantes( "./test/data/aspirantes2.dat" );
    }

    /**
     * Crea una bolsa de empleo vac�a
     */
    private void setupEscenario3( )
    {
        bolsa = new BolsaDeEmpleo( );
        cantidadAspirantes = 0;
    }

    /**
     * Verifica el m�todo agregarAspirante agregando correctamente un aspirante. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarAspirante, buscarAspirante, darAspirante. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarAspirante() sea capaz de registrar un aspirante en la bolsa de empleo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar (por nombre) un aspirante previamente agregado se debe obtener una posici�n diferente de -1 (se debe encontrar) y los datos del aspirante en esa posici�n
     * deben corresponder a los del aspirante con el nombre correspondiente. <br>
     * 
     */
    public void testAgregarAspirante1( )
    {
        // Configura los datos de prueba
        setupEscenario3( );

        String nombre;
        String profesion;
        int experiencia;
        int edad;
        String telefono;
        String imagen;

        boolean agregado;
        cantidadAspirantes = 10;

        // Agrega un aspirante y luego verifica que se haya agregado de forma correcta
        for( int cont = 1; cont <= cantidadAspirantes; cont++ )
        {
            nombre = "nombre" + cont;
            profesion = Aspirante.INGENIERO_INDUSTRIAL;
            experiencia = cont;
            edad = cont;
            telefono = "telefono" + cont;
            imagen = "imagen" + cont;

            agregado = bolsa.agregarAspirante( nombre, profesion, experiencia, edad, telefono, imagen );
            int pos = bolsa.buscarAspirante( nombre );
            Aspirante aspirante = ( Aspirante )bolsa.darAspirantes( ).get( pos );

            assertTrue( "El aspirante no se agreg� de forma correcta", agregado );
            assertEquals( "El aspirante no se agreg� de forma correcta", cont - 1, pos );
            assertEquals( "El aspirante no se agreg� de forma correcta", nombre, aspirante.darNombre( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", profesion, aspirante.darProfesion( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", experiencia, aspirante.darAniosExperiencia( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", edad, aspirante.darEdad( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", telefono, aspirante.darTelefono( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", imagen, aspirante.darImagen( ) );
        }
    }

    /**
     * Verifica el m�todo agregarAspirante agregando un aspirante con nombre repetido. <br>
     * <b> M�todos a probar: </b> <br>
     * agregarAspirante, buscarAspirante, darAspirante. <br>
     * <b> Objetivo: </b> Probar que el m�todo agregarAspirante() no agregue un aspirante en la bolsa de empleo cuando su nombre ya pertenece a otro aspirante registrado. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al agregar un aspirante con nombre repetido el n�mero de aspirantes se debe mantener igual y la informaci�n de los aspirantes existentes no debe haberse alterado.
     */
    public void testAgregarAspirante2( )
    {
        // Configura los datos de prueba
        setupEscenario1( );

        ArrayList aspirantes = bolsa.darAspirantes( );
        Aspirante a = ( Aspirante )aspirantes.get( 0 );
        String nombreAspirante = a.darNombre( );

        int i = Integer.parseInt( nombreAspirante );
        boolean agregado = bolsa.agregarAspirante( nombreAspirante, nombreAspirante, i, i, nombreAspirante, nombreAspirante );
        assertFalse( "El aspirante no deber�a haberse agregado", agregado );

        String nombre;
        String profesion;
        int experiencia;
        int edad;
        String telefono;
        String imagen;

        // Busca un aspirante y verifica que sus datos est�n correctos
        for( int cont = 0; cont < cantidadAspirantes; cont++ )
        {
            nombre = "" + ( cont + 1 );
            profesion = "Administrador";
            experiencia = cont + 1;
            edad = cont + 1;
            telefono = "" + ( cont + 1 );
            imagen = "" + ( cont + 1 );

            Aspirante aspirante = ( Aspirante )bolsa.darAspirantes( ).get( cont );

            assertEquals( "El aspirante no se agreg� de forma correcta", nombre, aspirante.darNombre( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", profesion, aspirante.darProfesion( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", experiencia, aspirante.darAniosExperiencia( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", edad, aspirante.darEdad( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", telefono, aspirante.darTelefono( ) );
            assertEquals( "El aspirante no se agreg� de forma correcta", imagen, aspirante.darImagen( ) );
        }

    }

    /**
     * Verifica el m�todo buscarAspirante buscando un aspirante que se sabe que deber�a encontrarse. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarAspirante. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarAspirante() sea capaz de encontrar aspirantes registrados en la bolsa de empleo. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un aspirante previamente agregado se debe obtener una posici�n diferente de -1. <br>
     * 2. Al buscar un aspirante que no exista la posici�n retornada debe ser -1.
     * 
     */
    public void testBuscarAspirante( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        bolsa.ordenarPorEdad( );
        ArrayList aspirantes = bolsa.darAspirantes( );
        Aspirante a0 = ( Aspirante )aspirantes.get( 0 );
        String nombreAspirante = a0.darNombre( );
        bolsa.ordenarPorProfesion( );

        int posicion = bolsa.buscarAspirante( nombreAspirante );
        assertTrue( "No se encontr� el aspirante", posicion != -1 );

        aspirantes = bolsa.darAspirantes( );
        Aspirante an = ( Aspirante )aspirantes.get( posicion );
        assertEquals( "No se encontr� el aspirante buscado", an.darNombre( ), nombreAspirante );

        posicion = bolsa.buscarAspirante( "el aspirante no existe" );
        assertEquals( "No se encontr� el aspirante buscado", -1, posicion );
    }

    /**
     * Verifica el m�todo buscarBinarioPorNombre buscando un aspirante que se sabe que deber�a encontrarse. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarBinarioPorNombre. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarBinarioPorNombre() sea capaz de encontrar aspirantes registrados en la exposici�n. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar un aspirante previamente agregado se debe obtener una posici�n diferente de -1. <br>
     * 2. Al buscar un aspirante que no exista la posici�n retornada debe ser -1.
     * 
     */
    public void testBuscarBinarioPorNombre( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        bolsa.ordenarPorNombre( );
        ArrayList aspirantes = bolsa.darAspirantes( );

        // Busca el primer aspirante
        Aspirante aspirante = ( Aspirante )aspirantes.get( 0 );
        String nombreAspirante = aspirante.darNombre( );

        int posicion = bolsa.buscarBinarioPorNombre( nombreAspirante );
        assertTrue( "No se encontr� el aspirante", posicion != -1 );

        Aspirante aspiranteNuevo = ( Aspirante )aspirantes.get( posicion );
        assertEquals( "No se encontr� el aspirante buscado", aspiranteNuevo.darNombre( ), nombreAspirante );

        // Busca el aspirante del medio
        aspirante = ( Aspirante )aspirantes.get( cantidadAspirantes / 2 );
        nombreAspirante = aspirante.darNombre( );

        posicion = bolsa.buscarBinarioPorNombre( nombreAspirante );
        assertTrue( "No se encontr� el aspirante", posicion != -1 );

        aspiranteNuevo = ( Aspirante )aspirantes.get( posicion );
        assertEquals( "No se encontr� el aspirante buscado", aspiranteNuevo.darNombre( ), nombreAspirante );

        // Busca el aspirante del final
        aspirante = ( Aspirante )aspirantes.get( cantidadAspirantes - 1 );
        nombreAspirante = aspirante.darNombre( );

        posicion = bolsa.buscarBinarioPorNombre( nombreAspirante );
        assertTrue( "No se encontr� el aspirante", posicion != -1 );

        aspiranteNuevo = ( Aspirante )aspirantes.get( posicion );
        assertEquals( "No se encontr� el aspirante buscado", aspiranteNuevo.darNombre( ), nombreAspirante );

        // Busca un aspirante que no existe
        posicion = bolsa.buscarAspirante( "el aspirante no existe" );
        assertEquals( "No se encontr� el aspirante buscado", -1, posicion );
    }

    /**
     * Verifica el m�todo para ordenar por a�os de experiencia. <br>
     * <b> M�todos a probar: </b> <br>
     * ordenarPorAniosDeExperiencia. <br>
     * <b> Objetivo: </b> Probar que el m�todo ordenarPorAniosDeExperiencia() ordena la bolsa de empleo de forma correcta (en orden ascendente por a�os de experiencia). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ordenar la bolsa de empleo por a�os de experiencia el aspirante con menor experiencia debe quedar de primero y el de mayor experiencia de �ltimo.
     * 
     */
    public void testOrdenarPorAniosDeExperiencia( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        bolsa.ordenarPorAniosDeExperiencia( );
        ArrayList aspirantes = bolsa.darAspirantes( );

        for( int i = 1; i < aspirantes.size( ); i++ )
        {
            Aspirante a0 = ( Aspirante )aspirantes.get( i - 1 );
            Aspirante a1 = ( Aspirante )aspirantes.get( i );

            assertTrue( "No se orden� bien por a�os de experiencia", a0.darAniosExperiencia( ) <= a1.darAniosExperiencia( ) );
        }
    }

    /**
     * Verifica el m�todo para ordenar por edad. <br>
     * <b> M�todos a probar: </b> <br>
     * ordenarPorEdad. <br>
     * <b> Objetivo: </b> Probar que el m�todo ordenarPorEdad() ordena la bolsa de empleo de forma correcta (en orden ascendente por edad). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ordenar la bolsa de empleo por edad el aspirante con menor edad debe quedar de primero y el de mayor edad de �ltimo.
     * 
     */
    public void testOrdenarPorEdad( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        bolsa.ordenarPorEdad( );
        ArrayList aspirantes = bolsa.darAspirantes( );
        for( int i = 1; i < aspirantes.size( ); i++ )
        {
            Aspirante a0 = ( Aspirante )aspirantes.get( i - 1 );
            Aspirante a1 = ( Aspirante )aspirantes.get( i );

            assertTrue( "No se orden� bien por edad", a0.darEdad( ) <= a1.darEdad( ) );
        }
    }

    /**
     * Verifica el m�todo de ordenar por profesi�n. <br>
     * <b> M�todos a probar: </b> <br>
     * ordenarPorProfesion. <br>
     * <b> Objetivo: </b> Probar que el m�todo ordenarPorProfesion() ordena la bolsa de empleo de forma correcta (en orden ascendente por profesi�n). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al ordenar la bolsa de empleo por profesi�n los aspirantes deben quedar ordenados por orden alfab�tico de acuerdo a su profesi�n. <br>
     * 
     */
    public void testOrdenarPorProfesion( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        bolsa.ordenarPorProfesion( );
        ArrayList aspirantes = bolsa.darAspirantes( );
        for( int i = 1; i < aspirantes.size( ); i++ )
        {
            Aspirante a0 = ( Aspirante )aspirantes.get( i - 1 );
            Aspirante a1 = ( Aspirante )aspirantes.get( i );

            assertTrue( "No se orden� bien por profesi�n", a0.darProfesion( ).compareTo( a1.darProfesion( ) ) <= 0 );
        }
    }

    /**
     * Verifica que el m�todo que busca el aspirante m�s joven funcione correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarAspiranteMasJoven. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarAspiranteMasJoven() retorna el aspirante correcto (el que tiene menor edad). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el aspirante con menor edad se debe obtener la posici�n del aspirante con la edad menor en la bolsa de empleo. <br>
     * 2. Al buscar el aspirante con menor edad en una bolsa vac�a la posici�n retornada debe ser -1.
     * 
     */
    public void testBuscarAspiranteMasJoven( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        int posMenor = bolsa.buscarAspiranteMasJoven( );
        ArrayList aspirantes = bolsa.darAspirantes( );
        Aspirante menorBusqueda = ( Aspirante )aspirantes.get( posMenor );

        bolsa.ordenarPorEdad( );
        aspirantes = bolsa.darAspirantes( );
        Aspirante menorOrdenamiento = ( Aspirante )aspirantes.get( 0 );

        assertEquals( "El aspirante de menor edad (el m�s joven) no es el correcto", menorBusqueda, menorOrdenamiento );

        setupEscenario3( );
        posMenor = bolsa.buscarAspiranteMasJoven( );
        assertEquals( "El aspirante de menor edad no debe existir", -1, posMenor );

    }

    /**
     * Verifica que el m�todo que busca el aspirante de mayor experiencia funcione correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * buscarAspiranteMayorExperiencia. <br>
     * <b> Objetivo: </b> Probar que el m�todo buscarAspiranteMayorExperiencia() retorna el aspirante correcto (el que tiene mayor experiencia). <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el aspirante con mayor experiencia se debe obtener la posici�n del aspirante con la experiencia m�s grande en la bolsa de empleo. <br>
     * 2. Al buscar el aspirante con mayor experiencia en una bolsa vac�a la posici�n retornada debe ser -1.
     * 
     */
    public void testBuscarAspiranteMayorExperiencia( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        int posMayor = bolsa.buscarAspiranteMayorExperiencia( );
        ArrayList aspirantes = bolsa.darAspirantes( );
        Aspirante mayorBusqueda = ( Aspirante )aspirantes.get( posMayor );

        bolsa.ordenarPorAniosDeExperiencia( );
        aspirantes = bolsa.darAspirantes( );
        Aspirante mayorOrdenamiento = ( Aspirante )aspirantes.get( aspirantes.size( ) - 1 );

        assertEquals( "El aspirante de mayor experiencia no es el correcto", mayorBusqueda, mayorOrdenamiento );

        setupEscenario3( );
        posMayor = bolsa.buscarAspiranteMayorExperiencia( );
        assertEquals( "El aspirante de mayor experiencia no debe existir", -1, posMayor );

    }

    /**
     * Verifica que el m�todo que contrata un aspirante funcione correctamente. <br>
     * <b> M�todos a probar: </b> <br>
     * contratarAspirante. <br>
     * <b> Objetivo: </b> Probar que el m�todo contratarAspirante() elimina al aspirante de la lista. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Al buscar el aspirante que se contrat� la posici�n retornada debe ser -1. <br>
     * 
     */
    public void testContratarAspirante( )
    {
        // Configura los datos de prueba
        setupEscenario2( );

        bolsa.ordenarPorProfesion( );
        ArrayList aspirantes = bolsa.darAspirantes( );
        Aspirante a0 = ( Aspirante )aspirantes.get( 0 );
        String nombreAspirante = a0.darNombre( );
        bolsa.ordenarPorEdad( );

        bolsa.contratarAspirante( nombreAspirante );
        int posicionEncontrado = bolsa.buscarAspirante( nombreAspirante );
        assertTrue( "No se contrat� bien al aspirante", posicionEncontrado == -1 );
    }

    /**
     * Verifica que el m�todo que un aspirante por su experiencia. <br>
     * <b> M�todos a probar: </b> <br>
     * eliminarAspirantesPorExperiencia. <br>
     * <b> Objetivo: </b> Probar que el m�todo eliminarAspirantesPorExperiencia() elimina correctamente los aspirantes de la lista. <br>
     * de acuerdo a los a�os de experiencia dados. <br>
     * <b> Resultados esperados: </b> <br>
     * 1. Se sabe que el n�mero de aspirantes con menos de X a�os de experiencia es Z. Al eliminar por experiencia los<br>
     * los participantes con menos de X a�os de experiencia se deben borrar Z aspirantes.
     * 
     */
    public void testEliminarAspirantesPorExperiencia( )
    {
        setupEscenario2( );

        assertEquals( "Se debieron eliminar 3 aspirantes", 3, bolsa.eliminarAspirantesPorExperiencia( 5 ) );

        assertEquals( "Se debieron eliminar 3 aspirantes", cantidadAspirantes - 3, bolsa.darAspirantes( ).size( ) );

        setupEscenario1( );

        assertEquals( "Se debi� eliminar 1 aspirante", 1, bolsa.eliminarAspirantesPorExperiencia( 2 ) );
        assertEquals( "Se debi� eliminar 1 aspirante", cantidadAspirantes - 1, bolsa.darAspirantes( ).size( ) );
    }

    // -----------------------------------------------------------------
    // M�todos Auxiliares
    // -----------------------------------------------------------------

    /**
     * Carga los aspirantes de la bolsa de empleo especificada a partir de un archivo de propiedades.
     * @param archivo es el nombre del archivo de propiedades que contiene la informaci�n de los aspirantes
     */
    private void cargarAspirantes( String archivo )
    {

        try
        {
            FileInputStream fis = new FileInputStream( new File( archivo ) );
            Properties propiedades = new Properties( );
            propiedades.load( fis );

            // Cargar los aspirantes
            String dato;
            String nombre;
            String profesion;
            int experiencia;
            int edad;
            String telefono;
            String imagen;
            String aux;
            dato = "total.aspirantes";
            aux = propiedades.getProperty( dato );
            cantidadAspirantes = Integer.parseInt( aux );

            for( int cont = 1; cont <= cantidadAspirantes; cont++ )
            {
                // Carga un aspirante
                dato = "aspirante" + cont + ".nombre";
                nombre = propiedades.getProperty( dato );

                dato = "aspirante" + cont + ".profesion";
                profesion = propiedades.getProperty( dato );

                dato = "aspirante" + cont + ".experiencia";
                aux = propiedades.getProperty( dato );
                experiencia = Integer.parseInt( aux );

                dato = "aspirante" + cont + ".edad";
                aux = propiedades.getProperty( dato );
                edad = Integer.parseInt( aux );

                dato = "aspirante" + cont + ".telefono";
                telefono = propiedades.getProperty( dato );

                dato = "aspirante" + cont + ".imagen";
                imagen = propiedades.getProperty( dato );

                bolsa.agregarAspirante( nombre, profesion, experiencia, edad, telefono, imagen );
                fis.close( );
            }
        }
        catch( Exception e )
        {

            fail( "No se pudo cargar el archivo de aspirantes: " + e.getMessage( ) );
        }
    }

}