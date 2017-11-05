(function (ng) {
    var mod = ng.module("bibliotecaModule");
    mod.constant("bibliotecasContext", "api/999/bibliotecas");
    mod.controller('bibliotecaNewCtrl', ['$scope', '$http', 'bibliotecasContext', '$state', '$rootScope',
        function ($scope, $http, bibliotecasContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createBiblioteca = function () {
                $http.post(bibliotecasContext, {
                    name: $scope.bibliotecaName,
                    ubicacion: $scope.bibliotecaUbicacion,
                    imagen: $scope.bibliotecaImagen
                }).then(function (response) {
                    //Biblioteca created successfully
                    $state.go('bibliotecasList', {bibliotecaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
