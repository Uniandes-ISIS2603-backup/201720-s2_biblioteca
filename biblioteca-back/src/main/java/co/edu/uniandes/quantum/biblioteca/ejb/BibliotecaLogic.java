/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.quantum.biblioteca.ejb;

import co.edu.uniandes.quantum.biblioteca.entities.BibliotecaEntity;
import co.edu.uniandes.quantum.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.quantum.biblioteca.persistence.BibliotecaPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class BibliotecaLogic {

    private static final Logger LOGGER = Logger.getLogger(BibliotecaLogic.class.getName());

    @Inject
    private BibliotecaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     *
     * @param entity
     * @return
     * @throws BusinessLogicException
     */
    public BibliotecaEntity createBiblioteca(BibliotecaEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de Biblioteca");  
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Biblioteca");
        return entity;
    }

    /**
     * 
     * Obtener todas las Bibliotecaes existentes en la base de datos.
     *
     * @return una lista de Bibliotecaes.
     */
    public List<BibliotecaEntity> getBibliotecas() {
        LOGGER.info("Inicia proceso de consultar todas las Bibliotecaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<BibliotecaEntity> Bibliotecas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las Bibliotecaes");
        return Bibliotecas;
    }


}
