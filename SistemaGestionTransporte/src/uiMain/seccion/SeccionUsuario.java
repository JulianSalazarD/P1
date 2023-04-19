package uiMain.seccion;

import gestorAplicaciones.entidades.Usuario;
import gestorAplicaciones.producto.Factura;
import uiMain.Main;

public class SeccionUsuario implements Seccion {
    int opcion = 0; //toma el valor de la opcion ingresado por consola
    Usuario usuario; //insatncia de usuario, representa el usaurio que ha ingresado.
    @Override
    public void Inicio() {
        /*
        Se tienen las opciones de salir al menu pricipal, iniciar seccion de usuario,
        registar un nuevo usuario o salir al menu principal en Main
         */
        do {
            System.out.println(" \nIngrese:\n1. iniciar Seccion.\n2. Registrarse.\n0. salir.");
            this.opcion = Main.getOption();
            switch (this.opcion) {
                case 0 ->
                    //salir
                        System.out.println("Vuelva pronto");
                case 1 ->
                    //Ingresar
                        this.ingresar();
                case 2 ->
                    //Registar usuario
                        this.registrarUSuario();
                default -> System.out.println("Opcion no valida.\n");
            }
        }while(this.opcion != 0);
    }

    @Override
    public void showMenu() {
        //mostar el tipo de acciones que puede realizar el usuario.
        do{
            System.out.println("\nIngrese:\n1. Realizar pedido.\n2. Seguir pedido.\n3. historial de pedido.\n4. PQRS.\n0. salir.");
            this.opcion = Main.getOption();
            switch (this.opcion) {
                case 0:
                    System.out.println("Seccion terminada. Vuelva pronto");
                    break;
                case 1:
                    /*
                    Realizar pedido
                     */
                    break;
                case 2:
                    /*
                    seguimiento de pedido (funcionalidad)
                     */
                    break;
                case 3:
                    //mostar historial de pedidos
                    Factura.historialFacturas(this.usuario);
                    break;
                case 4:
                    /*
                    //funcionalidad PQRS
                     */
                    break;
                default:
                    System.out.println("opcion no valida.");
            }
        }while(this.opcion != 0);

    }

    @Override
    public void guardar() {

    }

    @Override
    public void ingresar() {
        /*
        Esta funcion pide dos datos, usuario y clave, usario puede ser el nombre o id del usario,
         y clave es la clave del usuario y comprueba que los datos ingresador correspondan
         a un usuario ya existente.
         */
        String usuario,clave;

        //pedir usuario o numero de identificacion
        System.out.println("USuario/ID: ");
        usuario = Main.pedirDato();

        //pedir clave
        System.out.println("clave: ");
        clave = Main.pedirDato();

        //si los datos son validos llama a la funcion showMenu() si no sale de la funcion

        this.usuario = this.validarInformacion(usuario,clave);
        if (this.usuario != null) {
            this.showMenu();
        }
        else{
            System.out.println("Usuario y/o clave no validas");
        }
    }

    @Override
    public  Usuario validarInformacion(String usuario, String clave) {
        /*
        Esta funcion recibe dos String y retorna un objeto de tipo Usuario
        que coincida con los datos ingresados, de lo contrario retorna null.
         */

        if(usuario != null && usuario.chars().allMatch(c -> c == ' ' || Character.isLetter(c))){
            for (Usuario u : Usuario.getUsuarios()){
                if(u.comprobarUsuario(usuario,clave)) return u;
            }
        }
        else if(usuario != null && usuario.chars().allMatch(Character::isDigit)){
            for (Usuario u : Usuario.getUsuarios()){
                if(u.comprobarUsuario(Long.parseLong(usuario),clave)) return u;
            }
        }
        return null;
    }

    public void registrarUSuario(){
        /*
        Esta funcion registra un nuervo usuario en caso de que se cumplan
        todas las condiciones
        */
        String nombre, clave, id, correo;

        //pedir nombre
        System.out.println("nombre: ");
        nombre = Main.pedirDato();
        if(!this.isNombreValido(nombre)) return;

        //pedir numero de identificacion
        System.out.println("ID: ");
        id = Main.pedirDato();
        if(!this.isIDValido(id)) return;

        //pedir correo
        System.out.println("correo: ");
        correo = Main.pedirDato();
        if(!this.isCorreoValido(correo)) return;

        //pedir clave
        System.out.println("Clave: ");
        clave = Main.pedirDato();
        this.usuario = Usuario.crearUsuario(nombre, clave, Long.parseLong(id), correo);
        this.showMenu();

    }
    private boolean isNombreValido(String nombre){
        /*
        Esta funcion retorna false si el argumento que se ingresa no contione espacio y caracteres
        alfabetiscos o si ya es un atributo de nombre de un objeto de tipo usuario,
        de lo contrario retorna true.
         */
        if(nombre == null || !nombre.chars().allMatch(c -> c == ' ' || Character.isLetter(c))){
            System.out.println("El nombre solo debe tener caracteres alfanumericos.");
            return false;
        }
        if(Usuario.comprobarNombre(nombre)){
            System.out.println("nombre ya registrado.");
            return false;
        }
        return true;

    }

    private boolean isIDValido(String id){
        /*
        Esta funcion retorna false si el argumento que se ingresa no contione solo caracteres
        numericos o si ya es un atributo de ID de un objeto de tipo usuario,
        de lo contrario retorna true.
         */
        if(id == null || !id.chars().allMatch(Character::isDigit)){
            System.out.println("la identificacion debe contener solo caracteres numericos.");
            return false;
        }
        if(Usuario.comprobarID(Long.parseLong(id))){
            System.out.println("numero de identificacion ya registrado.");
            return false;
        }
        return true;
    }

    private boolean isCorreoValido(String correo){
        /*
        Esta funcion retorna false si el argumento que se ingresa contiene el caracter '@' o si
        solo contiene en la ultima posicion, de lo contrario retorna true.
         */
        if(!correo.contains("@") || correo.charAt(correo.length()-1) == '@'){
            System.out.println("correo no valido.");
            return false;
        }
        if(Usuario.comprobarCorreo(correo)){
            System.out.println("correo ya registrado.");
            return false;
        }
        return true;
    }
}
