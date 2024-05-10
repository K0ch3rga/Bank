package org.example;

public class CommandHandler {
    private class Command {
        public Command(String name, String description, String example) {
            Name = name;
            Description = description;
            Example = example;
        }

        public String Name;
        public String Description;
        public String Example;
    }

    private final Command[] m_ComandList = {
            new Command("/up_balance", "Пополнение баланса", "/up_balance <номер_счета> <сумма>"),
            new Command("/withdraw_balance", "Снять деньги со счета", "/withdraw_balance <номер_счета> <сумма>"),
            new Command("/transfer_money", "Перевод средств", "/transfer_money <номер_своего_счета> <номер_счета_получателя> <сумма>"),
            new Command("/open_new_account", "Открыть новый счет", "/open_new_account"),
            new Command("/show_balance", "Мой баланс", "/show_balance"),
            new Command("/help", "Вывести список команд", "/help")
    };


    public CommandHandler()
    {
    }

    public String Handle(String command, String[] arguments) {
        if (command.equals(m_ComandList[0].Name))
            return _UpBalance(arguments);
        else if (command.equals(m_ComandList[1].Name))
            return _WithdrawBalance(arguments);
        else if (command.equals(m_ComandList[2].Name))
            return _TransferMoney(arguments);
        else if (command.equals(m_ComandList[3].Name))
            return _OpenNewAccount();
        else if (command.equals(m_ComandList[4].Name))
            return _ShowBalance();
        else if (command.equals(m_ComandList[5].Name))
            return _Help();
        return "Не известная команда, попробуйте написать /help";
    }

    private String _UpBalance(String[] arguments) {
        if (arguments.length != 2)
            return GetSyntaxError(0);
        return "_UpBalance";
    }
    private String _WithdrawBalance(String[] arguments) {
        if (arguments.length != 2)
            return GetSyntaxError(1);
        return "_WithdrawBalance";
    }
    private String _TransferMoney(String[] arguments) {
        if (arguments.length != 3)
            return GetSyntaxError(2);
        return "_TransferMoney";
    }
    private String _OpenNewAccount() {
        return "_OpenNewAccount";
    }
    private String _ShowBalance() {
        return "_ShowBalance";
    }
    private String _Help() {
        String helpText = "Список команд:\n\n";
        for (int i = 0; i < m_ComandList.length; ++i)
            helpText += m_ComandList[i].Description + ":\n" + m_ComandList[i].Example + "\n\n";
        return helpText;
    }


    private String GetSyntaxError(int commandID) {
        String errorMessage = "Неправильный синтаксис команды " + m_ComandList[commandID].Name;
        errorMessage += "\nПример:\n" + m_ComandList[commandID].Example;
        return errorMessage;
    }
}
