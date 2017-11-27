(function (ng) {
    var mod = ng.module("librosModule");
    mod.constant("librosContext", "api/999/libros");
    mod.controller('libroNewCtrl', ['$scope', '$http', 'librosContext', '$state', '$rootScope',
        function ($scope, $http, librosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createLibro = function () {
                $http.post(librosContext, {
                    name: $scope.libroName,
                   anioPublicacion: $scope.libroAnioPublicacion,
                   numPaginas: $scope.libroNumeroPaginas,
                   imagen:$scope.libroImagen,
                   autor:$scope.libroAutor
                }).then(function (response) {
                    //Usuario created successfully
                    $state.go('librosList', {libroId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
