(function (ng) {
    // Definición del módulo
    var mod = ng.module("comentariosModule", ['ui.router']);
 mod.constant("comentariosContext", "api/999/comentario");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/comentario/';
            // Mostrar la lista de comentarios será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/comentariosList");
            // Definición del estado 'comentariosList' donde se listan los comentarios
            $stateProvider.state('comentario', {
                // Url que aparecerá en el browser
                url: '/comentario',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'comentarios.html',
                        controller: 'comentariosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
             }).state('comentariosList', {
                url: '/list',
                parent: 'comentarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'comentarios.list.html'
                    }
                }
            }).state('comentarioDetail', {
                url: '/{comentarioId:int}/detail',
                parent: 'comentarios',
                param: {
                    comentarioId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'comentarios.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'comentarios.detail.html',
                        controller: 'comentariosCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            }).state('comentariosCreate', {
                url: '/create',
                parent: 'comentarios',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/comentarios.new.html',
                        controller: 'comentarioNewCtrl'
                      }
            },
            data: {
                requireLogin: true,
                roles: ['admin']
            }
            }).state('comentarioDelete', {
                url: '/{comentarioId:int}/delete',
                parent: 'comentarios',
                param: {
                    comentarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/comentario.delete.html',
                        controller: 'comentariosDeleteCtrl'
                    }
                },
            data: {
                requireLogin: true,
                roles: ['admin']
            }
            }).state('comentarioUpdate', {
                url: '/{comentarioId:int}/update',
                parent: 'comentarios',
                param: {
                    comentarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/comentarios.new.html',
                        controller: 'comentariosUpdateCtrl'
                     }
            },
            data: {
                requireLogin: true,
                roles: ['admin', 'assistant']
            }
            });
        }]);
})(window.angular);
