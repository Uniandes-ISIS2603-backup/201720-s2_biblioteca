(function (ng) {
    // Definición del módulo
    var mod = ng.module("videosBibliotecaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/videoBiblioteca/';
            // Mostrar la lista de videos será el estado por defecto del módulo
           // $urlRouterProvider.otherwise("/videosList");
            // Definición del estado 'videosList' donde se listan los videos
            $stateProvider.state('videosBiblioteca', {
                // Url que aparecerá en el browser
                url: '/videosBiblioteca',
                abstract: true,
                 parent: 'bibliotecaDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'videosBiblioteca.html'
                     
                    }
                }
             }).state('videosBibliotecaList', {
                url: '/list',
                parent: 'videosBiblioteca',
                views: {
                    'listView': {
                        templateUrl: basePath + 'videosBiblioteca.list.html',
                         controller: 'videosBibliotecaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('videosBibliotecaCreate', {
                url: '/create',
                parent: 'videosBiblioteca',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/videosBiblioteca.new.html',
                        controller: 'videoBibliotecaNewCtrl'
                    }
                }
            });
        }]);
})(window.angular);
