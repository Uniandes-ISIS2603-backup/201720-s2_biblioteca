(function (ng) {
    // Definición del módulo
    var mod = ng.module("prestamoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/prestamo/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/prestamoList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('prestamos', {
                url: '/prestamos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'prestamos.html',
                        controller: 'prestamoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('prestamoList', {
                url: '/list',
                parent: 'prestamos',
                views: {
                    'listView': {
                        templateUrl: basePath + 'prestamos.list.html'
                    }
                }
            }).state('prestamoDetail', {
                url: '/{prestamoId:int}/detail',
                parent: 'prestamos',
                param: {
                    prestamoId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'prestamos.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'prestamos.detail.html',
                        controller: 'prestamoCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }
    ]);
})(window.angular);