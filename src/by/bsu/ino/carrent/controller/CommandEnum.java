package by.bsu.ino.carrent.controller;

import by.bsu.ino.carrent.command.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }

    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },
    CREATEORDER {
        {
            this.command = new CreateOrderCommand();
        }
    },
    SHOWFREECAR {
        {
            this.command = new FreeCarCommand();
        }
    },
    SHOWMYORDER {
        {
            this.command = new ShowCustomerOrderCommand();
        }
    },
    LANGUAGE {
        {
            this.command = new LanguageCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    USERADMINISTRATION {
        {
            this.command = new UserManageCommand();
        }
    },
    ORDERADMINISTRATION {
        {
            this.command = new AdminBookingCommand();
        }
    },
    SHOWMYBILL {
        {
            this.command = new ClientBillCommand();
        }
    },
    PAYMENT {
        {
            this.command = new PaymentCommand();
        }
    },
    CARADMINISTRATION {
        {
            this.command = new AllCarCommand();
        }
    },
    PAGE {
        {
            this.command = new PageCommand();
        }
    },
    RU
    {
        {
            this.command = new LanguageCommand();
        }
    },
    EN
    {
        {
            this.command = new LanguageCommand();
        }
    },
    CHANGELEVEL
            {
                {
                    this.command = new ChangeLevelCommand();
                }
            },
    CHANGEBOOKINGSTATE
            {
                {
                    this.command = new ChangeBookingStatusCommand();
                }
            };
    Command command;
    public Command getCurrentCommand() {
        return command;
    }
}
