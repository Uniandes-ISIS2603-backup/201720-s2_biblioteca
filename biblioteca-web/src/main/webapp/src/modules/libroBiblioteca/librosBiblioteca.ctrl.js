(function (ng) {
    var mod = ng.module("librosBibliotecaModule");
    mod.constant("librosBibliotecaContext", "api/999/libros/biblioteca");
       mod.constant("librosContext", "api/999/libros");
    mod.controller('librosBibliotecaCtrl', ['$scope', '$http', 'librosBibliotecaContext', '$state', 
        function ($scope, $http, librosBibliotecaContext, $state) {
            $http.get(librosBibliotecaContext+ "/"+ $state.params.bibliotecaId ).then(function (response) {
                $scope.libroBibliotecaRecords = response.data;
            });
            
          
            
        }
    ]);
}
)(angular);