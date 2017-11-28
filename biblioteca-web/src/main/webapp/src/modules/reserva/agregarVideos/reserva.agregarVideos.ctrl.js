(function (ng) {
    var mod = ng.module("reservaModule");
    mod.constant("reservaContext", "reservas");
    mod.constant("usuarioContext","api/999/usuarios");
    mod.controller('reservaAgregarVideosCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {           
            
            $http.get('api/999/videos/disponibles').then(function (response) {
                    $scope.allVideosDisponibles = response.data;
                });
                
                $scope.agregarVideoReserva = function (laId) {
                $http.put('api/999/usuarios/' + $state.params.usuarioId + '/reservas/' + $state.params.reservaId + '/videos/actual/reserva/' + laId+'/colocarVideo', {

                }).then(function (response) {
                    //Prestamo created successfully
                    $state.go('reservaList', {reservaId: response.data.id}, {reload: true});
                });
            };
            
            



            
        }
    ]);
}
)(angular);