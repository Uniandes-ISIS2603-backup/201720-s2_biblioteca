(function (ng) {
    // Definición del módulo
    var mod = ng.module("blogsModule", ['ui.router']);
 mod.constant("blogsContext", "api/999/blog");
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/blog/';
            // Mostrar la lista de blogs será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/blogsList");
            // Definición del estado 'blogsList' donde se listan los blogs
            $stateProvider.state('blogs', {
                // Url que aparecerá en el browser
                url: '/blog',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'blogs.html',
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl'
                    }
                }
             }).state('blogsList', {
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
                    'listView': {
                        templateUrl: basePath + 'blogs.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'blogs.detail.html',
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl'
                    }

                }

            }).state('blogsCreate', {
                url: '/create',
                parent: 'blogs',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/blogs.new.html',
                        controller: 'blogNewCtrl'
                      }
            },
            data: {
                requireLogin: true,
                roles: ['admin']
            }
            }).state('blogDelete', {
                url: '/{blogId:int}/delete',
                parent: 'blogs',
                param: {
                    blogId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/blog.delete.html',
                        controller: 'blogsDeleteCtrl'
                    }
                },
            data: {
                requireLogin: true,
                roles: ['admin']
            }
            }).state('blogUpdate', {
                url: '/{blogId:int}/update',
                parent: 'blogs',
                param: {
                    blogId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/blogs.new.html',
                        controller: 'blogsUpdateCtrl'
                     }
            },
            data: {
                requireLogin: true,
                roles: ['admin', 'assistant']
            }
            });
        }]);
})(window.angular);
