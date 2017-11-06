(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("reservaContext", "reservas");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('reservaAgregarLibrosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {           
            
            $http.get('api/999/libros/disponibles').then(function (response) {
                    $scope.allLibrosDisponibles = response.data;
                });
                
                $scope.agregarLibroReserva = function (laId) {
                $http.put('api/999/usuarios/' + $state.params.usuarioId + '/reservas/' + $state.params.reservaId + '/libros/actual/reserva/' + laId+'/colocarlibro', {

                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('reservaList', {reservaId: response.data.id}, {reload: true});
                });
            };
            
            



            
        }
    ]);
}
)(angular);