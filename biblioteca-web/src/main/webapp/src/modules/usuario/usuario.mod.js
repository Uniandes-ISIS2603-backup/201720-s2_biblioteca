(function (ng) {
    // Definición del módulo
    var mod = ng.module("usuarioModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/usuario/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/usuarioList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('usuarioList', {
                url: '/list',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'usuarios.list.html'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('usuarioDetail', {
                url: '/{usuarioId:int}/detail',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                views: {
                    
                    'detailView': {
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: 'usuarioCtrl',
                        controllerAs: 'ctrl'
                    }

                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('usuarioCreate', {
                url: '/create',
                parent: 'usuarios',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/usuarios.new.html',
                        controller: 'usuarioNewCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('usuarioUpdate', {
                url: '/{usuarioId:int}/update',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/usuarios.new.html',
                        controller: 'usuarioUpdateCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            }).state('usuarioDelete', {
                url: '/{usuarioId:int}/delete',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/usuario.delete.html',
                        controller: 'usuarioDeleteCtrl'
                    }
                },
                data: {
                    requireLogin: true,
                    roles: ['admin', 'assistant']
                }
            });
        }
    ]);
})(window.angular);