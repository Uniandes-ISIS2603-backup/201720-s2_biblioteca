(function (ng) {
    var mod = ng.module("prestamoModule");
    mod.constant("prestamoContext", "prestamos");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('prestamoAgregarLibrosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {           
            
            $http.get('api/999/libros/disponibles').then(function (response) {
                    $scope.allLibrosDisponibles = response.data;
                });
                
                $scope.agregarLibroPrestamo = function (laId) {
                $http.put('api/999/usuarios/' + $state.params.usuarioId + '/prestamos/' + $state.params.prestamoId + '/libros/actual/prestamo/' + laId, {

                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('prestamoList', {prestamoId: response.data.id}, {reload: true});
                });
            };
            
            



            
        }
    ]);
}
)(angular);