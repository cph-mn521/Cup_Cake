onerror = errorHandler;            
        function errorHandler(message, url, line)
            {
                out = "Sorry, an error was encountered. \n\n";
                out += "Error: " + message + "\n";
                out += "URL: " + url + "\n";
                out += "Line: " + line + "\n";
                out += "Click OK to continue.\n\n";
                alert(out);
                return true;
            }
