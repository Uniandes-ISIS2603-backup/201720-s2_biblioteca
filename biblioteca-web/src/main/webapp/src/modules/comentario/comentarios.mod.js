(function (ng) {
    // Definición del módulo
    var mod = ng.module("comentarioModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/comentario/';
            // Mostrar la lista de comentarios será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/comentarioList");
            // Definición del estado 'comentarioList' donde se listan los comentarios
            $stateProvider.state('comentario', {
                // Url que aparecerá en el browser
                url: '/comentario',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'comentarios.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('comentarioList', {
                url: '/list',
                parent: 'comentario',
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
                    
                    'detailView': {
                        templateUrl: basePath + 'comentarios.detail.html',
                        controller: 'comentarioCtrl',
                        controllerAs: 'ctrl'
                    }

                }
            }).state('comentarioCreate', {
                url: '/create',
                parent: 'comentarios',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/comentarios.new.html',
                        controller: 'comentarioNewCtrl'
                    }
                }
            }).state('comentarioUpdate', {
                url: '/{comentarioId:int}/update',
                parent: 'comentario',
                param: {
                    comentarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/comentarios.new.html',
                        controller: 'comentarioUpdateCrl'
                    }
                }
            }).state('comentarioDelete', {
                url: '/{comentarioId:int}/delete',
                parent: 'comentario',
                param: {
                    comentarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/comentarios.delete.html',
                        controller: 'comentarioDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);