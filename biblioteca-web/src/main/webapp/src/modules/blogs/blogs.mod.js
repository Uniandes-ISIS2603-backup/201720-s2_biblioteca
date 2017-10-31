(function (ng) {
    // Definición del módulo
    var mod = ng.module("blogModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/blog/';
            // Mostrar la lista de blog será el estado por defecto del módulo
            //$urlRouterProvider.otherwise("/blogList");
            // Definición del estado 'blogList' donde se listan los autores
            $stateProvider.state('blogList', {
                // Url que aparecerá en el browser
                url: '/blog',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'blogs.list.html',
                        controller: 'blogCtrl',
                        controllerAs: 'ctrl'
                      }
                }
            }).state('blogList', {
                url: '/list',
                parent: 'blogs',
                views: {
                    'listView': {
                        templateUrl: basePath + 'blogs.list.html'
                    }
                }
            }).state('blogDetail', {
                url: '/{blogId:int}/detail',
                parent: 'blogs',
                param: {
                    blogId: null
                },
                views: {
                    
                    'detailView': {
                        templateUrl: basePath + 'blogs.detail.html',
                        controller: 'blogCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            });
        }
    ]);
})(window.angular);