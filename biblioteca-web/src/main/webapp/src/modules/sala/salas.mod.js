(function (ng) {
    // Definición del módulo
    var mod = ng.module("salasModule", ['ui.router']);
 mod.constant("salasContext", "api/999/salas");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/sala/';
            // Mostrar la lista de salas será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/salasList");
            // Definición del estado 'salasList' donde se listan los salas
            $stateProvider.state('salas', {
                // Url que aparecerá en el browser
                url: '/salas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'salas.html',
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
             }).state('salasList', {
                url: '/list',
                parent: 'salas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'salas.list.html'
                    }
                }
            }).state('salaDetail', {
                url: '/{salaId:int}/detail',
                parent: 'salas',
                param: {
                    salaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'salas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'salas.detail.html',
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            }).state('salasCreate', {
                url: '/create',
                parent: 'salas',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/salas.new.html',
                        controller: 'salaNewCtrl'
                    }
                }
            }).state('salaDelete', {
                url: '/{salaId:int}/delete',
                parent: 'salas',
                param: {
                    salaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/sala.delete.html',
                        controller: 'salasDeleteCtrl'
                    }
                }
            }).state('salaUpdate', {
                url: '/{salaId:int}/update',
                parent: 'salas',
                param: {
                    salaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/salas.new.html',
                        controller: 'salasUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);
