(function (ng) {
    // Definición del módulo
    var mod = ng.module("librosModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/libro/';
            // Mostrar la lista de autores será el estado por defecto del módulo
           // $urlRouterProvider.otherwise("/librosList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('librosList', {
                // Url que aparecerá en el browser
                url: '/libros',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'libros.list.html',
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);