(function (ng) {
    // Definición del módulo
    var mod = ng.module("bibliotecaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/biblioteca/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/bibliotecasList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('bibliotecas', {
                url: '/bibliotecas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bibliotecas.html',
                        controller: 'bibliotecaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bibliotecasList', {
                // Url que aparecerá en el browser
                url: '/bibliotecas',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'bibliotecas.list.html',
                        controller: 'bibliotecaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('bibliotecaDetail', {
                url: '/{bibliotecaId:int}/detail',
                parent: 'bibliotecas',
                param: {
                    bibliotecaId: null
                },
                views: {
                    
                    'detailView': {
                        templateUrl: basePath + 'bibliotecas.detail.html',
                        controller: 'bibliotecaCtrl',
                        controllerAs: 'ctrl'
                    }

                }
            }).state('bibliotecaCreate', {
                url: '/create',
                parent: 'bibliotecas',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/bibliotecas.new.html',
                        controller: 'bibliotecaNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('bibliotecaUpdate', {
                url: '/{bibliotecaId:int}/update',
                parent: 'bibliotecas',
                param: {
                    bibliotecaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'new/bibliotecas.new.html',
                        controller: 'bibliotecaUpdateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('bibliotecaDelete', {
                url: '/{bibliotecaId:int}/delete',
                parent: 'bibliotecas',
                param: {
                    bibliotecaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'delete/bibliotecas.delete.html',
                        controller: 'bibliotecaDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
});;
        }
    ]);
})(window.angular);