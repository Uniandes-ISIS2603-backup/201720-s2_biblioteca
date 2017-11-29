(function (ng) {
    // Definición del módulo
    var mod = ng.module("comentariosBlogModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/comentarioBlog/';
            // Mostrar la lista de comentarios será el estado por defecto del módulo
           // $urlRouterProvider.otherwise("/comentariosList");
            // Definición del estado 'comentariosList' donde se listan los comentarios
            $stateProvider.state('comentariosBlog', {
                // Url que aparecerá en el browser
                url: '/comentariosBlog',
                abstract: true,
                 parent: 'blogDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'comentariosBlog.html'
                     
                    }
                }
             }).state('comentariosBlogList', {
                url: '/list',
                parent: 'comentariosBlog',
                views: {
                    'listView': {
                        templateUrl: basePath + 'comentariosBlog.list.html',
                         controller: 'comentariosBlogCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('comentariosBlogCreate', {
                url: '/create',
                parent: 'comentariosBlog',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/comentariosBlog.new.html',
                        controller: 'comentarioBlogNewCtrl'
                    }
                }
            });
        }]);
})(window.angular);
