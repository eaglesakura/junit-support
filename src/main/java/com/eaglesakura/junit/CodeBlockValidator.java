package com.eaglesakura.junit;

import com.eaglesakura.lambda.Action0;
import com.eaglesakura.lambda.ResultAction0;
import com.eaglesakura.util.StringUtil;
import com.eaglesakura.util.Timer;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * コードブロックを扱うvalidator
 */
public class CodeBlockValidator<ResultType> {
    String blockName;

    ResultAction0<ResultType> actionWithResult;

    Action0 action;

    List<Block> mBlocks = new LinkedList<>();

    public CodeBlockValidator(String blockName, ResultAction0<ResultType> action) {
        this.blockName = blockName;
        this.actionWithResult = action;
    }

    public CodeBlockValidator(String blockName, Action0 action) {
        this.blockName = blockName;
        this.action = action;
    }

    ResultType doAction() {
        try {
            if (actionWithResult != null) {
                return actionWithResult.action();
            } else {
                action.action();
                return null;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            fail(StringUtil.format("Action error[%s]", blockName));
            throw new Error();
        }
    }

    public CodeBlockValidator<ResultType> pushBlock(String name) {
        mBlocks.add(new Block(name));
        return this;
    }

    public CodeBlockValidator<ResultType> popBlock(String name, long timeoutMs) {
        if (timeoutMs <= 0) {
            timeoutMs = 1000 * 60 * 60 * 24;
        }

        // ブロックパスを生成する
        String fullName = name;
        for (Block block : mBlocks) {
            fullName += ("/" + block.name);
        }

        Block block = mBlocks.remove(mBlocks.size() - 1);

        String message = StringUtil.format("block[%s] ideal[ < %d ms ] runtime[ %d ms = %.1f sec ]", blockName, timeoutMs, block.timer.end(), block.timer.endSec());
        if (block.timer.end() > timeoutMs) {
            fail(message);
        }

        return this;
    }

    class Block {
        String name;

        Timer timer = new Timer();

        public Block(String name) {
            this.name = name;
        }
    }

    public ResultType execute() {
        return executeTimeLess(-1);
    }

    /**
     * 時間以内に処理が完了していることを確認する
     */
    public ResultType executeTimeLess(long timeoutMs) {
        if (timeoutMs <= 0) {
            timeoutMs = 1000 * 60 * 60 * 24;
        }

        Timer timer = new Timer();
        ResultType result = doAction();
        String message = StringUtil.format("block[%s] ideal[ < %d ms ] runtime[ %d ms = %.1f sec ]", blockName, timeoutMs, timer.end(), timer.endSec());
        if (timer.end() <= timeoutMs) {
            return result;
        } else {
            fail(message);
            throw new Error();
        }
    }
}
