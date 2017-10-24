(function (ng) {
    // Definición del módulo
    var mod = ng.module("salaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/salas/';
            // Mostrar la lista de salas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/salaList");
            // Definición del estado 'salasList' donde se listan los salas
            $stateProvider.state('salasList', {
                // Url que aparecerá en el browser
                url: '/salas/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'salas.list.html',
                        controller: 'salaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);