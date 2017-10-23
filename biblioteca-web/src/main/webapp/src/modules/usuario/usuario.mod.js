function (ng) {
    // Definición del módulo
    var mod = ng.module("usuarioModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/usuario/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/usuarioList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('usuarioList', {
                // Url que aparecerá en el browser
                url: '/pruebaz',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.list.html',
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
}(window.angular);