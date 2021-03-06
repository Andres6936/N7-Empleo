package edu.jobs.mundo;

import java.util.ArrayList;

/**
 * Es la clase que se encarga de manejar y organizar los aspirantes <br>
 * <b>inv: </b> <br>
 * aspirantes != null <br>
 * En el vector de aspirantes no hay dos o mós con el mismo nombre
 */
public class BolsaDeEmpleo
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la lista que contiene todos los aspirantes
     */
    private ArrayList< Aspirante > aspirantes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva bolsa de emplea sin aspirantes.
     */
    public BolsaDeEmpleo( )
    {
        aspirantes = new ArrayList<>( );

    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Retorna una lista de aspirantes. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
     * @return lista de aspirantes
     */
    public ArrayList< Aspirante > darAspirantes( )
    {
        return new ArrayList<>( aspirantes );
    }

    /**
     * Organiza la lista de aspirantes por nombre usando el algoritmo de burbuja. <br>
     * <b>post: </b>La lista de aspirantes estó ordenada por nombre (orden ascendente).
     */
    public void ordenarPorNombre( )
    {
        for( int i = aspirantes.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Aspirante a1 = aspirantes.get( j );
                Aspirante a2 = aspirantes.get( j + 1 );

                // Si es necesario se deben intercambiar a1 y a2
                if( a1.compararPorNombre( a2 ) > 0 )
                {
                    aspirantes.set( j, a2 );
                    aspirantes.set( j + 1, a1 );
                }
            }
        }
        verificarInvariante( );
    }

    /**
     * Organiza la lista de aspirantes por edad usando el algoritmo de selección <br>
     * <b>post: </b>La lista de aspirantes estó ordenada por edad
     */
    public void ordenarPorEdad( )
    {
        int inicial;
        // En cada iteración se sabe que:
        // 1. Las posiciones antes de aspirantes[inicial] estón ordenadas por edad
        // 2. No hay ningón valor despuós de aspirantes[inicial-1] que sea menor que aspirantes[inicial-1]
        // En cada iteración se busca el menor entre aspirantes[inicial] y aspirantes[final] y se ubica en aspirantes[inicial]

        for( inicial = 0; inicial < aspirantes.size( ); inicial++ )
        {
            int posicionMenor = inicial;
            Aspirante aspiranteMenor = aspirantes.get( inicial );

            // Buscar el Aspirante de menor [int, Edad, Seleccion] entre inicial y final
            for( int i = inicial + 1; i < aspirantes.size( ); i++ )
            {
                Aspirante aspirantePosicion = aspirantes.get( i );

                // El aspirante de la posicion actual es menor que el menor encontrado hasta el momento
                if( aspirantePosicion.compararPorEdad( aspiranteMenor ) < 0 )
                {
                    aspiranteMenor = aspirantePosicion;
                    posicionMenor = i;
                }
            }

            if( posicionMenor != inicial )
            {
                Aspirante temp = aspirantes.get( inicial );
                aspirantes.set( inicial, aspiranteMenor );
                aspirantes.set( posicionMenor, temp );
            }

        }
        verificarInvariante( );
    }

    /**
     * Organiza la lista de aspirantes por profesión usando el algoritmo de burbuja <br>
     * <b>post: </b>El conjunto de aspirantes esta ordenado por profesión
     */
    public void ordenarPorProfesion( )
    {
        for( int i = aspirantes.size( ); i > 0; i-- )
        {
            for( int j = 0; j < i - 1; j++ )
            {
                Aspirante a1 = aspirantes.get( j );
                Aspirante a2 = aspirantes.get( j + 1 );

                // Si es necesario se deben intercambiar a1 y a2
                if( a1.compararPorProfesion( a2 ) > 0 )
                {
                    aspirantes.set( j, a2 );
                    aspirantes.set( j + 1, a1 );
                }
            }
        }
        verificarInvariante( );
    }

    /**
     * Organiza la lista de aspirantes por aóos de experiencia usando el algoritmo de inserción <br>
     * <b>post: </b>La lista de aspirantes estó ordenada por aóos de experiencia
     */
    public void ordenarPorAniosDeExperiencia( )
    {
        int inicial;

        // En cada paso se sabe que:
        // 1. Las posiciones antes de aspirantes[inicial] estón ordenadas
        // En cada paso lo que se hace es encontrar en quó posición entre aspirantes[0] y aspirantes[inicial] deberóa
        // estar el aspirante que en este momento se encuentra en aspirante[inicial]

        for( inicial = 1; inicial < aspirantes.size( ); inicial++ )
        {
            Aspirante insertado = aspirantes.get( inicial );

            boolean termine = false;
            int i = inicial - 1;

            while( !termine )
            {
                // Si encuentra un cilindraje mayor, entonces hay que intercambiarlos
                Aspirante aspirantePosicion = aspirantes.get( i );

                if( aspirantePosicion.compararPorAniosExperiencia( insertado ) > 0 )
                {
                    aspirantes.set( i, insertado );
                    aspirantes.set( i + 1, aspirantePosicion );
                    i--;
                }
                // Si se encuentra un cilindraje igual o menor entonces ya se encontró la posición
                else
                {
                    termine = true;
                }

                // Si ya se llegó al principio de la lista no hay nada mós que hacer
                if( i < 0 )
                {
                    termine = true;
                }
            }
        }
        verificarInvariante( );
    }

    /**
     * Busca un Aspirante segón su nombre y retorna la posición en la que se encuentra. <br>
     * @param nombre El nombre del aspirante buscado - nombre!=null
     * @return Se retornó la posición donde se encuentra un aspirante con el nombre dado. Si no se encuentra ningón aspirante con ese nombre se retornó -1.
     */
    public int buscarAspirante( String nombre )
    {
        int posicion = -1;
        boolean termine = false;

        for( int i = 0; i < aspirantes.size( ) && !termine; i++ )
        {
            Aspirante aspirantePosicion = aspirantes.get( i );
            String nombreAspirante = aspirantePosicion.darNombre( );

            // Los nombres son iguales
            if( nombreAspirante.equalsIgnoreCase( nombre ) )
            {
                posicion = i;
                termine = true;
            }
        }

        return posicion;
    }

    /**
     * Busca un aspirante utilizando una bósqueda binaria. <br>
     * <b>pre: </b> La lista de aspirantes se encuentra ordenada por nombre. <br>
     * @param nombre es el nombre del aspirante que se va a buscar - nombre!=null
     * @return Se retornó la posición del aspirante con el nombre dado. Si el aspirante no existe se retornó -1.
     */
    public int buscarBinarioPorNombre( String nombre )
    {
        int posicion = -1;
        int inicio = 0;
        int fin = aspirantes.size( ) - 1;

        Aspirante aBuscar = new Aspirante( nombre, Aspirante.INGENIERO_INDUSTRIAL, 1, 1, "", "" );
        while( inicio <= fin && posicion == -1 )
        {
            int medio = ( inicio + fin ) / 2;
            Aspirante mitad = aspirantes.get( medio );
            if( mitad.compararPorNombre( aBuscar ) == 0 )
            {
                posicion = medio;
            }
            else if( mitad.compararPorNombre( aBuscar ) > 0 )
            {
                fin = medio - 1;
            }
            else
            {
                inicio = medio + 1;
            }
        }
        return posicion;
    }

    /**
     * Agrega un nuevo aspirante a la bolsa
     * @param nombreA El nombre del aspirante - nombreA != null
     * @param profesionA La profesión del aspirante - profesionA es uno de estos { ADMINISTRADOR, INGENIERO_INDUSTRIAL, CONTADOR, ECONOMISTA }
     * @param aniosExperienciaA Aóos de experiencia del aspirante - aniosExperienciaA > 0
     * @param edadA La edad del aspirante - edadA > 0
     * @param telefonoA El telófono del aspirante - telefonoA != null
     * @param imagenA La ruta a la imagen del aspirante - imagenA != null
     * @return Se retornó true si el aspirante fue adicionado o false de lo contrario
     */

    public boolean agregarAspirante( String nombreA, String profesionA, int aniosExperienciaA, int edadA, String telefonoA, String imagenA )
    {
        int aspiranteBuscado = buscarAspirante( nombreA );
        boolean agregado = false;
        if( aspiranteBuscado == -1 )
        {
            Aspirante nuevoAspirante = new Aspirante( nombreA, profesionA, aniosExperienciaA, edadA, telefonoA, imagenA );
            aspirantes.add( nuevoAspirante );
            agregado = true;

        }

        verificarInvariante( );

        return agregado;
    }

    /**
     * Busca el aspirante que tenga la menor edad en la bolsa.
     * @return Se retornó la posición donde se encuentra el aspirante mós joven. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMasJoven( )
    {
        int posicion = -1;

        if( aspirantes.size( ) > 0 )
        {
            Aspirante aMenorEdad = aspirantes.get( 0 );
            posicion = 0;
            for( int i = 1; i < aspirantes.size( ); i++ )
            {
                Aspirante aspirantePosicion = aspirantes.get( i );

                if( aMenorEdad.compararPorEdad( aspirantePosicion ) == 1 )
                {
                    posicion = i;
                    aMenorEdad = aspirantePosicion;
                }
            }
        }

        return posicion;
    }

    /**
     * Busca el aspirante que tenga la mayor edad en la bolsa.
     * @return Se retornó la posición donde se encuentra el aspirante con mós edad. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMayorEdad( )
    {
        int posicion = -1;

        if( aspirantes.size( ) > 0 )
        {
            Aspirante aMayorEdad = aspirantes.get( 0 );
            posicion = 0;
            for( int i = 1; i < aspirantes.size( ); i++ )
            {
                Aspirante aspirantePosicion = aspirantes.get( i );

                if( aMayorEdad.compararPorEdad( aspirantePosicion ) == -1 )
                {
                    posicion = i;
                    aMayorEdad = aspirantePosicion;
                }
            }
        }

        return posicion;
    }

    /**
     * Busca el aspirante con mós aóos de experiencia en la bolsa.
     * @return Se retornó la posición donde se encuentra el aspirante con mayor experiencia. Si no hay aspirantes en la bolsa se retornó -1
     */
    public int buscarAspiranteMayorExperiencia( )
    {
        int posicion = -1;

        if( aspirantes.size( ) > 0 )
        {
            Aspirante aMayorExp = aspirantes.get( 0 );
            posicion = 0;
            for( int i = 1; i < aspirantes.size( ); i++ )
            {
                Aspirante aspirantePosicion = aspirantes.get( i );

                if( aMayorExp.compararPorAniosExperiencia( aspirantePosicion ) == -1 )
                {
                    posicion = i;
                    aMayorExp = aspirantePosicion;
                }
            }
        }

        return posicion;
    }

    /**
     * Contrata a un aspirante.<br>
     * <b>post: </b>Se eliminó el aspirante de la lista de aspirantes.
     * @param nombre El nombre del aspirante a contratar - nombre!=null
     * @return Se retornó true si el aspirante estaba registrado en la bolsa o false de lo contrario
     */
    public boolean contratarAspirante( String nombre )
    {
        boolean contratado = false;

        if( !aspirantes.isEmpty( ) )
        {
            int posicionAspirante = buscarAspirante( nombre );
            if( posicionAspirante != -1 )
            {
                aspirantes.remove( posicionAspirante );
                contratado = true;
            }
        }

        verificarInvariante( );

        return contratado;
    }

    /**
     * Elimina todos los aspirantes de la bolsa cuyos aóos de experiencia <br>
     * son menores a la cantidad de aóos especificada <br>
     * @param aniosExperiencia La cantidad de aóos con relación a la cual se van a eliminar los aspirantes. aniosExperiencia>=0
     * @return La cantidad de aspirantes que fueron eliminados
     */
    public int eliminarAspirantesPorExperiencia( int aniosExperiencia )
    {
        int eliminados = 0;

        int cont = 0;

        while( cont < aspirantes.size( ) )
        {
            Aspirante aspirante = aspirantes.get( cont );

            if( aspirante.darAniosExperiencia( ) < aniosExperiencia )
            {
                aspirantes.remove( cont );
                eliminados++;
            }
            else
            {
                cont++;
            }
        }

        verificarInvariante( );

        return eliminados;
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase <br>
     * <b>inv </b><br>
     * aspirantes != null <br>
     * no hay dos aspirantes con el mismo nombre
     */
    private void verificarInvariante( )
    {
        assert ( aspirantes != null ) : "La lista de aspirantes no debe ser null";
        assert ( !buscarAspirantesConNombresRepetidos( ) ) : "Hay dos aspirantes con el mismo nombre";
    }

    /**
     * Verifica si hay dos aspirantes con el mismo nombre
     * @return Se retornó true si hay dos aspirantes con el mismo nombre; se retornó false en caso contrario.
     */
    private boolean buscarAspirantesConNombresRepetidos( )
    {
        for( int i = 0; i < aspirantes.size( ); i++ )
        {
            Aspirante aspiranteI = aspirantes.get( i );
            for( int j = 0; j < aspirantes.size( ); j++ )
            {
                if( i != j )
                {
                    Aspirante aspiranteJ = aspirantes.get( j );
                    if( aspiranteJ.darNombre( ).equals( aspiranteI.darNombre( ) ) )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Ejecuta el punto de extensión 1
     * @return Respuesta 1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Ejecuta el punto de extensión 2
     * @return Respuesta 2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }

}