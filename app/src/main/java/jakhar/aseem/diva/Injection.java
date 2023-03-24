// ruleid:command_injection
Process process = Runtime.getRuntime().exec("ping somewhere.com" + foo);

Process process = Runtime.getRuntime().exec("ping somewhere.com");
