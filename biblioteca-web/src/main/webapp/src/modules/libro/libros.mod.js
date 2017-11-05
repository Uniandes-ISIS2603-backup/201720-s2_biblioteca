(function (ng) {
    // Definición del módulo
    var mod = ng.module("librosModule", ['ui.router']);
 mod.constant("librosContext", "api/999/libros");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/libro/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/librosList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('libros', {
                // Url que aparecerá en el browser
                url: '/libros',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'libros.html',
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
             }).state('librosList', {
                url: '/list',
                parent: 'libros',
                views: {
                    'listView': {
                        templateUrl: basePath + 'libros.list.html'
                    }
                }
            }).state('libroDetail', {
                url: '/{libroId:int}/detail',
                parent: 'libros',
                param: {
                    libroId: null
                },
                views: {
                    
                    'detailView': {
                        templateUrl: basePath + 'libros.detail.html',
                        controller: 'librosCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            }).state('librosCreate', {
                url: '/create',
                parent: 'libros',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/libros.new.html',
                        controller: 'libroNewCtrl'
                    }
                }
            }).state('libroDelete', {
                url: '/{libroId:int}/delete',
                parent: 'libros',
                param: {
                    libroId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/libro.delete.html',
                        controller: 'librosDeleteCtrl'
                    }
                }
}).state('libroUpdate', {
                url: '/{libroId:int}/update',
                parent: 'libros',
                param: {
                    usuarioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/libros.new.html',
                        controller: 'librosUpdateCtrl'
                    }
                }
            });
        }]);
})(window.angular);
