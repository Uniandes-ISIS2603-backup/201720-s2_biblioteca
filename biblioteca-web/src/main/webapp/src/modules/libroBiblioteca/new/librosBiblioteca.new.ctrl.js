(function (ng) {
    var mod = ng.module("librosModule");
    mod.constant("librosBibliotecaCrearContext", "api/999/libros/bibliotecaCrear");
    mod.controller('libroBibliotecaNewCtrl', ['$scope', '$http', 'librosBibliotecaCrearContext', '$state', '$rootScope',
        function ($scope, $http, librosBibliotecaCrearContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createLibro = function () {
                $http.post(librosBibliotecaCrearContext + "/"+ $state.params.bibliotecaId, {
                    name: $scope.libroName,
                   anioPublicacion: $scope.libroAnioPublicacion,
                   numPaginas: $scope.libroNumeroPaginas
                }).then(function (response) {
                    //Usuario created successfully
                    $state.go('librosBibliotecaList', {libroId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
