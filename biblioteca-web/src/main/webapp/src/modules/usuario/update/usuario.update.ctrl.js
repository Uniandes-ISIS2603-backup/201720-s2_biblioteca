(
        function (ng) {
            var mod = ng.module("usuarioModule");
            mod.constant("usuarioContext", "api/999/usuarios");
            mod.constant("prestamoContext", "api/books");
            mod.controller('usuarioUpdateCtrl', ['$scope', '$http', 'usuariosContext', '$state', 'prestamoContext', '$rootScope', '$filter',
                function ($scope, $http, usuarioContext, $state, prestamoContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idUsuario = $state.params.usuarioId;
                     $scope.dataNewUsuario={};    

                    //Consulto el autor a editar.
                    console.info(usuarioContext+'/' + idUsuario);
                        $http.get(usuarioContext + '/' + idUsuario).then(function (response) {
                        var usuario = response.data;
                        $scope.dataNewUsuario.name = usuario.name;
                        $scope.dataNewUsuario.telefono = usuario.telefono;
                        $scope.dataNewUsuario.direccion = usuario.direccion;
                    });    
                    
                                   

                    $scope.createUsuario = function () {                   
                        $http.put(usuarioContext + "/" + idUsuario, $scope.dataNewUsuario).then(function (response) {                            
                            $state.go('usuarioList', {usuarioId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);